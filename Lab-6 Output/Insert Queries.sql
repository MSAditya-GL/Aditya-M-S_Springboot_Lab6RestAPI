SELECT * FROM student_event_registration.roles;
insert into roles values (1, "USER");
insert into roles values (2, "ADMIN");
insert into users values (1,"$2a$12$Fb4pUorje9vRu/70ZkM9GuJD.HxeDEyTYrwY5xQZxJcrAKDFVTgPi", "admin");
insert into users values (2,"$2a$12$y4BkToXp7MAJJfD65anHjObucTSnrWuQ9W7UhQW8fmQF.KH9jwDt6", "user");
insert into user_roles values (1,2);
insert into user_roles values (2,1);