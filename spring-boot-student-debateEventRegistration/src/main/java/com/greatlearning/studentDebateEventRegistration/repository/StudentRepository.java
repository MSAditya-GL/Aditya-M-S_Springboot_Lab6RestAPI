package com.greatlearning.studentDebateEventRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.studentDebateEventRegistration.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
