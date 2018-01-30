
DROP SCHEMA IF EXISTS `SuperVillains_test` ;

-- -----------------------------------------------------
-- Schema SuperVillains_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SuperVillains_test` DEFAULT CHARACTER SET utf8 ;
USE `SuperVillains_test` ;

-- -----------------------------------------------------
-- Table `SuperVillains_test`.`SuperVillain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`SuperVillain` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`SuperVillain` (
  `Villain_id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Villain_id`));


-- -----------------------------------------------------
-- Table `SuperVillains_test`.`Powers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`Powers` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`Powers` (
  `power_id` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`power_id`));


-- -----------------------------------------------------
-- Table `SuperVillains_test`.`VillainPower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`VillainPower` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`VillainPower` (
  `Villain_id` INT NOT NULL,
  `power_id` INT NOT NULL,
  PRIMARY KEY (`Villain_id`, `power_id`),
  CONSTRAINT `vp_Villain_id`
    FOREIGN KEY (`Villain_id`)
    REFERENCES `SuperVillains_test`.`SuperVillain` (`Villain_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vp_power_id`
    FOREIGN KEY (`power_id`)
    REFERENCES `SuperVillains_test`.`Powers` (`power_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperVillains_test`.`Orgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`Orgs` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`Orgs` (
  `org_id` INT NOT NULL AUTO_INCREMENT,
  `Org_Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(95) NOT NULL,
  `Email` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  PRIMARY KEY (`org_id`));


-- -----------------------------------------------------
-- Table `SuperVillains_test`.`OrgVillain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`OrgVillain` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`OrgVillain` (
  `org_id` INT NOT NULL,
  `Villain_id` INT NOT NULL,
  PRIMARY KEY (`org_id`, `Villain_id`),
  CONSTRAINT `ov_Villain_id`
    FOREIGN KEY (`Villain_id`)
    REFERENCES `SuperVillains_test`.`SuperVillain` (`Villain_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ov_org_id`
    FOREIGN KEY (`org_id`)
    REFERENCES `SuperVillains_test`.`Orgs` (`org_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperVillains_test`.`Locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`Locations` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`Locations` (
  `loc_id` INT NOT NULL AUTO_INCREMENT,
  `Loc_Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NULL,
  `Address` VARCHAR(105) NOT NULL,
  `Latitude` FLOAT(30, 15) NULL,
  `longitude` FLOAT(30, 15) NULL,
  PRIMARY KEY (`loc_id`));


-- -----------------------------------------------------
-- Table `SuperVillains_test`.`Sightings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`Sightings` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`Sightings` (
  `sighting_id` INT NOT NULL AUTO_INCREMENT,
  `loc_id` INT NULL,
  `DateTime` DATETIME NOT NULL,
  PRIMARY KEY (`sighting_id`),
  CONSTRAINT `loc_id`
    FOREIGN KEY (`loc_id`)
    REFERENCES `SuperVillains_test`.`Locations` (`loc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperVillains_test`.`SightingsVillains`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains_test`.`SightingsVillains` ;

CREATE TABLE IF NOT EXISTS `SuperVillains_test`.`SightingsVillains` (
  `sighting_id` INT NOT NULL,
  `Villain_id` INT NOT NULL,
  PRIMARY KEY (`sighting_id`, `Villain_id`),
  CONSTRAINT `sighting_id`
    FOREIGN KEY (`sighting_id`)
    REFERENCES `SuperVillains_test`.`Sightings` (`sighting_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vilian_id`
    FOREIGN KEY (`Villain_id`)
    REFERENCES `SuperVillains_test`.`SuperVillain` (`Villain_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

