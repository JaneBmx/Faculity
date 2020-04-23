SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


DROP SCHEMA IF EXISTS `Faculty` ;
CREATE SCHEMA IF NOT EXISTS `Faculty` DEFAULT CHARACTER SET utf8 ;
USE `Faculty` ;


DROP TABLE IF EXISTS `Faculty`.`roles` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`role_id`));
CREATE UNIQUE INDEX `role_id_UNIQUE` ON `Faculty`.`roles` (`role_id` ASC) VISIBLE;
CREATE UNIQUE INDEX `role_name_UNIQUE` ON `Faculty`.`roles` (`role_name` ASC) VISIBLE;



DROP TABLE IF EXISTS `Faculty`.`privileges` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`privileges` (
  `privilege_id` INT NOT NULL AUTO_INCREMENT,
  `privilege_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`privilege_id`));
CREATE UNIQUE INDEX `privilege_id_UNIQUE` ON `Faculty`.`privileges` (`privilege_id` ASC) VISIBLE;
CREATE UNIQUE INDEX `privilege_name_UNIQUE` ON `Faculty`.`privileges` (`privilege_name` ASC) VISIBLE;



DROP TABLE IF EXISTS `Faculty`.`users` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_role_id` INT NOT NULL,
  `user_name` VARCHAR(40) NOT NULL,
  `user_surname` VARCHAR(40) NOT NULL,
  `user_email` VARCHAR(40) NOT NULL,
  `user_login` VARCHAR(40) NOT NULL,
  `user_password` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_role_fk`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `Faculty`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE UNIQUE INDEX `user_id_UNIQUE` ON `Faculty`.`users` (`user_id` ASC) VISIBLE;
CREATE UNIQUE INDEX `user_login_UNIQUE` ON `Faculty`.`users` (`user_login` ASC) VISIBLE;
CREATE UNIQUE INDEX `user_email_UNIQUE` ON `Faculty`.`users` (`user_email` ASC) VISIBLE;
CREATE INDEX `user_role_idx` ON `Faculty`.`users` (`user_role_id` ASC) VISIBLE;



DROP TABLE IF EXISTS `Faculty`.`facultues` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`facultues` (
  `faculty_id` INT NOT NULL AUTO_INCREMENT,
  `faculty_name` VARCHAR(40) NOT NULL,
  `free_accept_plan` INT NOT NULL,
  `paid_accept_plan` INT NOT NULL,
  PRIMARY KEY (`faculty_id`));
CREATE UNIQUE INDEX `faculty_id_UNIQUE` ON `Faculty`.`facultues` (`faculty_id` ASC) VISIBLE;
CREATE UNIQUE INDEX `faculty_name_UNIQUE` ON `Faculty`.`facultues` (`faculty_name` ASC) VISIBLE;


DROP TABLE IF EXISTS `Faculty`.`grade_reports` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`grade_reports` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `faculty_id` INT NOT NULL,
  `is_accepted` TINYINT(1) NOT NULL,
  `is_free_paid` TINYINT(1) NOT NULL,
  `privilege_id` INT NOT NULL,
  `attestat_mark` DECIMAL(2,1) NOT NULL,
  `averege_mark` DECIMAL(2,1) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `privilege_id_fk`
    FOREIGN KEY (`privilege_id`)
    REFERENCES `Faculty`.`privileges` (`privilege_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `Faculty`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `faculty_id_fk`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `Faculty`.`facultues` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE UNIQUE INDEX `user_id_UNIQUE` ON `Faculty`.`grade_reports` (`user_id` ASC) VISIBLE;
CREATE INDEX `privilege_id_idx` ON `Faculty`.`grade_reports` (`privilege_id` ASC) VISIBLE;
CREATE INDEX `faculty_id_idx` ON `Faculty`.`grade_reports` (`faculty_id` ASC) VISIBLE;



DROP TABLE IF EXISTS `Faculty`.`subjects` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`subjects` (
  `subject_id` INT NOT NULL,
  `subject_name` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`subject_id`));
CREATE UNIQUE INDEX `subject_name_UNIQUE` ON `Faculty`.`subjects` (`subject_name` ASC) VISIBLE;
CREATE UNIQUE INDEX `subject_id_UNIQUE` ON `Faculty`.`subjects` (`subject_id` ASC) VISIBLE;



DROP TABLE IF EXISTS `Faculty`.`faculty2subject` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`faculty2subject` (
  `subject_id` INT NOT NULL,
  `faculty_id` INT NOT NULL,
  CONSTRAINT `faculty_id_fk_2`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `Faculty`.`facultues` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `subject_id_fk`
    FOREIGN KEY (`subject_id`)
    REFERENCES `Faculty`.`subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX `faculty_id_idx` ON `Faculty`.`faculty2subject` (`faculty_id` ASC) VISIBLE;
CREATE INDEX `subject_id_idx` ON `Faculty`.`faculty2subject` (`subject_id` ASC) VISIBLE;



DROP TABLE IF EXISTS `Faculty`.`grade_report2subject` ;
CREATE TABLE IF NOT EXISTS `Faculty`.`grade_report2subject` (
  `user_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `mark` INT NOT NULL,
  CONSTRAINT `subject_id_fk_2`
    FOREIGN KEY (`subject_id`)
    REFERENCES `Faculty`.`subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `Faculty`.`grade_reports` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX `subject_id_idx` ON `Faculty`.`grade_report2subject` (`subject_id` ASC) VISIBLE;
CREATE INDEX `user_id_idx` ON `Faculty`.`grade_report2subject` (`user_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;