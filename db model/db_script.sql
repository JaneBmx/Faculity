-- MySQL Script generated by MySQL Workbench
-- Sat Mar 28 20:01:44 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Faculty
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Faculty` ;

-- -----------------------------------------------------
-- Schema Faculty
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Faculty` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `Faculty` ;

-- -----------------------------------------------------
-- Table `Faculty`.`faculties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`faculties` (
  `faculty_id` INT NOT NULL AUTO_INCREMENT,
  `faculty_name` VARCHAR(100) NOT NULL,
  `free_accept_plan` INT(4) NOT NULL DEFAULT 0,
  `paid_accept_plan` INT(4) NOT NULL,
  PRIMARY KEY (`faculty_id`));

SHOW WARNINGS;
CREATE UNIQUE INDEX `faculty_id_UNIQUE` ON `Faculty`.`faculties` (`faculty_id` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Faculty`.`grade_report2subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`grade_report2subject` (
  `user_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `mark` INT(2) NOT NULL,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `Faculty`.`grade_reports` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `Faculty`.`subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;
CREATE INDEX `user_id_idx` ON `Faculty`.`grade_report2subject` (`user_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `subject_id_idx` ON `Faculty`.`grade_report2subject` (`subject_id` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Faculty`.`grade_reports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`grade_reports` (
  `user_id` INT NOT NULL,
  `faculty_id` INT NOT NULL,
  `isAccepted` TINYINT(1) NOT NULL DEFAULT 0,
  `isFreePaid` TINYINT(1) NOT NULL DEFAULT 0,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `Faculty`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `faculty_id`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `Faculty`.`faculties` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;
CREATE UNIQUE INDEX `user_id_UNIQUE` ON `Faculty`.`grade_reports` (`user_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `faculty_id_idx` ON `Faculty`.`grade_reports` (`faculty_id` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Faculty`.`privileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`privileges` (
  `privilege_id` INT NOT NULL DEFAULT 0,
  `privilege_name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`privilege_id`));

SHOW WARNINGS;
CREATE UNIQUE INDEX `privilege_name_UNIQUE` ON `Faculty`.`privileges` (`privilege_name` ASC) VISIBLE;

SHOW WARNINGS;
CREATE UNIQUE INDEX `privilege_id_UNIQUE` ON `Faculty`.`privileges` (`privilege_id` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Faculty`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`roles` (
  `role_id` INT NOT NULL DEFAULT 4,
  `role_name` VARCHAR(15) NULL,
  PRIMARY KEY (`role_id`));

SHOW WARNINGS;
CREATE UNIQUE INDEX `role_id_UNIQUE` ON `Faculty`.`roles` (`role_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE UNIQUE INDEX `role_name_UNIQUE` ON `Faculty`.`roles` (`role_name` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Faculty`.`subject2faculty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`subject2faculty` (
  `subject_id` INT NOT NULL,
  `faculty_id` INT NOT NULL,
  CONSTRAINT `subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `Faculty`.`subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `faculty_id`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `Faculty`.`faculties` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;
CREATE INDEX `subject_id_idx` ON `Faculty`.`subject2faculty` (`subject_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `faculty_id_idx` ON `Faculty`.`subject2faculty` (`faculty_id` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Faculty`.`subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`subjects` (
  `subject_id` INT NOT NULL AUTO_INCREMENT,
  `subject_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`subject_id`));

SHOW WARNINGS;
CREATE UNIQUE INDEX `subject_id_UNIQUE` ON `Faculty`.`subjects` (`subject_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE UNIQUE INDEX `subject_name_UNIQUE` ON `Faculty`.`subjects` (`subject_name` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Faculty`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Faculty`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_role` INT NOT NULL DEFAULT 4,
  `user_surname` VARCHAR(40) NOT NULL,
  `user_name` VARCHAR(40) NOT NULL,
  `user_email` VARCHAR(40) NOT NULL,
  `user_login` VARCHAR(40) NOT NULL,
  `user_password` VARCHAR(40) NOT NULL,
  `user_privilege` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_role`
    FOREIGN KEY (`user_role`)
    REFERENCES `Faculty`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_privilege`
    FOREIGN KEY (`user_privilege`)
    REFERENCES `Faculty`.`privileges` (`privilege_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;
CREATE INDEX `user_role_idx` ON `Faculty`.`users` (`user_role` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `user_privilege_idx` ON `Faculty`.`users` (`user_privilege` ASC) VISIBLE;

SHOW WARNINGS;
CREATE UNIQUE INDEX `user_id_UNIQUE` ON `Faculty`.`users` (`user_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE UNIQUE INDEX `user_login_UNIQUE` ON `Faculty`.`users` (`user_login` ASC) VISIBLE;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
