START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`roles` (`role_id`, `role_name`) VALUES (1, 'admin');
INSERT INTO `Faculty`.`roles` (`role_id`, `role_name`) VALUES (2, 'user');
INSERT INTO `Faculty`.`roles` (`role_id`, `role_name`) VALUES (3, 'guest');
COMMIT;


START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`privileges` (`privilege_id`, `privilege_name`) VALUES (1, 'Олимпиадник');
INSERT INTO `Faculty`.`privileges` (`privilege_id`, `privilege_name`) VALUES (2, 'Сирота');
INSERT INTO `Faculty`.`privileges` (`privilege_id`, `privilege_name`) VALUES (3, 'Красный диплом');
INSERT INTO `Faculty`.`privileges` (`privilege_id`, `privilege_name`) VALUES (4, 'Иностранец');
INSERT INTO `Faculty`.`privileges` (`privilege_id`, `privilege_name`) VALUES (5, 'СУЗ');
INSERT INTO `Faculty`.`privileges` (`privilege_id`, `privilege_name`) VALUES (6, 'Нет');
COMMIT;


START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (1, 1, 'Самый', 'Главный', 'admin@tmail.com', 'admin', 'Admin12345');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (2, 2, 'Василий', 'Пупкин', 'vasya@tmail.com', 'vasya', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (3, 2, 'Петр', 'Васин', 'petya@tmail.com', 'petya', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (4, 2, 'Иммануил', 'Например', 'immanu@tmail.com', 'imma', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (5, 2, 'Еватерина', 'Великая', 'katya@tmail.com', 'katya', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (6, 2, 'Студент', 'Типичный', 'nespal@tmail.com', 'student', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (7, 2, 'Олимпиада', 'Умная', 'oly@tmail.com', 'olimp', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (8, 2, 'Отличник', 'Усердный', 'medal@tmail.com', 'cool', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (9, 2, 'Олег ', 'Иванов', 'zazaza@tmail.com', 'hmmm', 'QWErty1234');
INSERT INTO `Faculty`.`users` (`user_id`, `user_role_id`, `user_name`, `user_surname`, `user_email`, `user_login`, `user_password`) VALUES (10, 2, 'Иван', 'Бездомный', 'neochen@tmail.com', 'ivan', 'QWErty1234');
COMMIT;


START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (1, 'Факультет математики', 5, 10);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (2, 'Факультет физики', 5, 10);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (3, 'Факультет геологических наук', 3, 15);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (4, 'Факультет филологии', 2, 8);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (5, 'Факультет иностранных языков', 5, 20);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (6, 'Факультет компьютерного проектирования', 5, 25);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (7, 'Факультет естествознания', 0, 10);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (8, 'Факультет педагогики и фоспитания', 0, 10);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (9, 'Факультет бесполезный', 0, 20);
INSERT INTO `Faculty`.`facultues` (`faculty_id`, `faculty_name`, `free_accept_plan`, `paid_accept_plan`) VALUES (10, 'Факультет дизайна', 5, 10);
COMMIT;


START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (2, 9, 0, 0, 6, 4, 7);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (3, 9, 0, 0, 6, 9, 8);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (4, 8, 0, 0, 4, 9, 9);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (5, 8, 0, 0, 4, 6, 8);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (6, 8, 0, 0, 5, 4, 7);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (7, 7, 0, 0, 1, 6, 8);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (8, 7, 0, 0, 3, 9, 9);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (9, 7, 0, 0, 5, 8, 8);
INSERT INTO `Faculty`.`grade_reports` (`user_id`, `faculty_id`, `is_accepted`, `is_free_paid`, `privilege_id`, `attestat_mark`, `averege_mark`) VALUES (10, 7, 0, 0, 2, 7, 7);
COMMIT;


START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (1, 'Математика');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (2, 'Физика');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (3, 'География');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (4, 'Геология');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (5, 'Русский язык');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (6, 'Английский язык');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (7, 'Немецкий язык');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (8, 'Испанский язык');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (9, 'Французский язык');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (10, 'Информатика');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (11, 'История');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (12, 'Искусство');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (13, 'Химия');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (14, 'Биология');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (15, 'Обществознание');
INSERT INTO `Faculty`.`subjects` (`subject_id`, `subject_name`) VALUES (16, 'Невалидный');
COMMIT;


START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (1, 1);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (2, 1);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 1);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (2, 2);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (1, 2);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 2);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (3, 3);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (4, 3);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 3);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 4);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (6, 4);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (15, 4);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 5);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (6, 5);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (7, 5);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (1, 6);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (6, 6);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (10, 6);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 7);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (13, 7);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (14, 7);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 8);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (11, 8);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (15, 8);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (7, 9);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (8, 9);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (9, 9);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (5, 10);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (12, 10);
INSERT INTO `Faculty`.`faculty2subject` (`subject_id`, `faculty_id`) VALUES (15, 10);
COMMIT;


START TRANSACTION;
USE `Faculty`;
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (2, 7, 7);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (2, 8, 7);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (2, 9, 7);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (3, 7, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (3, 8, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (3, 9, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (4, 5, 9);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (4, 11, 9);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (4, 15, 9);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (5, 5, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (5, 11, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (5, 15, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (6, 5, 7);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (6, 11, 7);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (6, 15, 7);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (7, 5, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (7, 13, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (7, 14, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (8, 5, 9);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (8, 13, 9);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (8, 14, 9);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (9, 5, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (9, 13, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (9, 14, 8);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (10, 5, 6);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (10, 13, 7);
INSERT INTO `Faculty`.`grade_report2subject` (`user_id`, `subject_id`, `mark`) VALUES (10, 14, 8);
COMMIT;