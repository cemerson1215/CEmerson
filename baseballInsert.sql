-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema baseball
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `baseball` ;

-- -----------------------------------------------------
-- Schema baseball
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baseball` DEFAULT CHARACTER SET utf8 ;
USE `baseball` ;

-- -----------------------------------------------------
-- Table `baseball`.`Manufacturers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`Manufacturers` ;

CREATE TABLE IF NOT EXISTS `baseball`.`Manufacturers` (
  `manufactId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`manufactId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`Cards`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`Cards` ;

CREATE TABLE IF NOT EXISTS `baseball`.`Cards` (
  `cardId` INT NOT NULL AUTO_INCREMENT,
  `card_number` INT NOT NULL,
  `players_name` VARCHAR(45) NULL,
  `year` INT NOT NULL,
  `manufactuer_id` INT NOT NULL,
  PRIMARY KEY (`cardId`),
  INDEX `manufactuer_id_idx` (`manufactuer_id` ASC),
  CONSTRAINT `manufactuer_id`
    FOREIGN KEY (`manufactuer_id`)
    REFERENCES `baseball`.`Manufacturers` (`manufactId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
