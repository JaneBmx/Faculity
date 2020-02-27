-- -----------------------------------------------------
-- Data for table `Elective`.`faculity`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`faculity` (`faculity_id`, `faculity_name`, `free_aducation_plan`, `paid_aducation_plan`) VALUES (01, 'Math', 10, 30);
INSERT INTO `Elective`.`faculity` (`faculity_id`, `faculity_name`, `free_aducation_plan`, `paid_aducation_plan`) VALUES (02, 'Physics', 5, 15);
INSERT INTO `Elective`.`faculity` (`faculity_id`, `faculity_name`, `free_aducation_plan`, `paid_aducation_plan`) VALUES (03, 'Geographic', 3, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`gradereport`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`gradereport` (`gradereport_id`, `enrollee_id`, `attestat`, `isAccepted`, `isFree`) VALUES (01, 01, 9, NULL, NULL);
INSERT INTO `Elective`.`gradereport` (`gradereport_id`, `enrollee_id`, `attestat`, `isAccepted`, `isFree`) VALUES (02, 02, 7,8, NULL, NULL);
INSERT INTO `Elective`.`gradereport` (`gradereport_id`, `enrollee_id`, `attestat`, `isAccepted`, `isFree`) VALUES (03, 03, 8, NULL, NULL);
INSERT INTO `Elective`.`gradereport` (`gradereport_id`, `enrollee_id`, `attestat`, `isAccepted`, `isFree`) VALUES (04, 03, 8,  , NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`gradereport2subject`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (01, 04, 9);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (01, 05, 8);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (01, 03, 6);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (02, 01, 8);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (02, 02, 7);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (02, 03, 9);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (03, 01, 8);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (03, 03, 9);
INSERT INTO `Elective`.`gradereport2subject` (`gradereport_id`, `subject_id`, `mark`) VALUES (03, 02, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`privelege`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`privelege` (`privelege_id`, `privelege`) VALUES (01, 'Olympiad');
INSERT INTO `Elective`.`privelege` (`privelege_id`, `privelege`) VALUES (02, 'Medal');
INSERT INTO `Elective`.`privelege` (`privelege_id`, `privelege`) VALUES (03, 'Foreign');
INSERT INTO `Elective`.`privelege` (`privelege_id`, `privelege`) VALUES (04, 'Orphan');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`role` (`role_id`, `role`) VALUES (01, 'Admin');
INSERT INTO `Elective`.`role` (`role_id`, `role`) VALUES (02, 'Teacher');
INSERT INTO `Elective`.`role` (`role_id`, `role`) VALUES (03, 'Enrollee');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`subject2faculity`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (01, 01, 1);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (01, 02, 2);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (01, 03, 3);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (02, 01, 2);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (02, 02, 1);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (02, 03, 3);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (03, 04, 1);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (03, 05, 2);
INSERT INTO `Elective`.`subject2faculity` (`faculity_id`, `subject_id`, `priority`) VALUES (03, 03, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`subjects`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`subjects` (`subject_id`, `subject_name`) VALUES (01, 'Maths');
INSERT INTO `Elective`.`subjects` (`subject_id`, `subject_name`) VALUES (02, 'Physics');
INSERT INTO `Elective`.`subjects` (`subject_id`, `subject_name`) VALUES (03, 'Russian');
INSERT INTO `Elective`.`subjects` (`subject_id`, `subject_name`) VALUES (04, 'Geography');
INSERT INTO `Elective`.`subjects` (`subject_id`, `subject_name`) VALUES (05, 'Biology');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`user` (`user_id`, `role`, `name`, `surname`, `email`, `login`, `pasword`, `privilege`) VALUES (01, 03, 'Василий', 'Васин', 'vasya@mail.ru', 'qwerty', 'Qwerty', NULL);
INSERT INTO `Elective`.`user` (`user_id`, `role`, `name`, `surname`, `email`, `login`, `pasword`, `privilege`) VALUES (02, 03, 'Peter', 'Pettor', 'pit@gmail.com', 'asdfgh', 'Asdfgh', 03);
INSERT INTO `Elective`.`user` (`user_id`, `role`, `name`, `surname`, `email`, `login`, `pasword`, `privilege`) VALUES (03, 03, 'Ivan', 'Ivanov', 'vanya@rambler.ru', 'zxcvbn', 'Zxcvbn', 01);
INSERT INTO `Elective`.`user` (`user_id`, `role`, `name`, `surname`, `email`, `login`, `pasword`, `privilege`) VALUES (04, 01, 'Admin', 'Admin', 'admin@university.com', 'Admin', 'Admin', NULL);
INSERT INTO `Elective`.`user` (`user_id`, `role`, `name`, `surname`, `email`, `login`, `pasword`, `privilege`) VALUES (05, 02, 'Иммануил', 'Сигизмундович', 'prepod@university.com', 'Prepod', 'Prepod', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Elective`.`user2faculity`
-- -----------------------------------------------------
START TRANSACTION;
USE `Elective`;
INSERT INTO `Elective`.`user2faculity` (`user_id`, `faculity_id`) VALUES (01, 03);
INSERT INTO `Elective`.`user2faculity` (`user_id`, `faculity_id`) VALUES (02, 01);
INSERT INTO `Elective`.`user2faculity` (`user_id`, `faculity_id`) VALUES (03, 02);
INSERT INTO `Elective`.`user2faculity` (`user_id`, `faculity_id`) VALUES (03, 01);

COMMIT;

