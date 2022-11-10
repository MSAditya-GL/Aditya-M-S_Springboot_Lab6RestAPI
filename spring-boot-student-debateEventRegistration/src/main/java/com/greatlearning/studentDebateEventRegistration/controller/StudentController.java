package com.greatlearning.studentDebateEventRegistration.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentDebateEventRegistration.entity.Student;
import com.greatlearning.studentDebateEventRegistration.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudent(Model theModel) {

		List<Student> eventReg = studentService.findAll();

		theModel.addAttribute("Students", eventReg);

		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theID, Model theModel) {
		Student theStudent = studentService.findById(theID);
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;

		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		} else {
			theStudent = new Student(firstName, lastName, course, country);
		}

		studentService.save(theStudent);

		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {

		studentService.deleteById(theId);

		return "redirect:/student/list";
	}

	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			Model theModel) {

		// check names, if both are empty then just give list of all Books

		if (firstName.trim().isEmpty() && lastName.trim().isEmpty()) {
			return "redirect:/student/list";

		} else {
			// else, search by first name and last name
			List<Student> students = studentService.searchBy(firstName, lastName);

			// add to the spring model
			theModel.addAttribute("Students", students);

			// send to list-Students
			return "list-Students";
		}
	}

	@RequestMapping("/print")

	public String listStudents(Model theModel) {

		List<Student> eventReg = studentService.findAll();

		theModel.addAttribute("Students", eventReg);

		return "Print-List";
	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}
