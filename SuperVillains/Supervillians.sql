
DROP SCHEMA IF EXISTS `SuperVillains` ;

-- -----------------------------------------------------
-- Schema SuperVillains
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SuperVillains` DEFAULT CHARACTER SET utf8 ;
USE `SuperVillains` ;

-- -----------------------------------------------------
-- Table `SuperVillains`.`SuperVillain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`SuperVillain` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`SuperVillain` (
  `Villain_id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Villain_id`));


-- -----------------------------------------------------
-- Table `SuperVillains`.`Powers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`Powers` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`Powers` (
  `power_id` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`power_id`));


-- -----------------------------------------------------
-- Table `SuperVillains`.`VillainPower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`VillainPower` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`VillainPower` (
  `Villain_id` INT NOT NULL,
  `power_id` INT NOT NULL,
  PRIMARY KEY (`Villain_id`, `power_id`),
  CONSTRAINT `vp_Villain_id`
    FOREIGN KEY (`Villain_id`)
    REFERENCES `SuperVillains`.`SuperVillain` (`Villain_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vp_power_id`
    FOREIGN KEY (`power_id`)
    REFERENCES `SuperVillains`.`Powers` (`power_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperVillains`.`Orgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`Orgs` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`Orgs` (
  `org_id` INT NOT NULL AUTO_INCREMENT,
  `Org_Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(95) NOT NULL,
  `Email` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  PRIMARY KEY (`org_id`));


-- -----------------------------------------------------
-- Table `SuperVillains`.`OrgVillain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`OrgVillain` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`OrgVillain` (
  `org_id` INT NOT NULL,
  `Villain_id` INT NOT NULL,
  PRIMARY KEY (`org_id`, `Villain_id`),
  CONSTRAINT `ov_Villain_id`
    FOREIGN KEY (`Villain_id`)
    REFERENCES `SuperVillains`.`SuperVillain` (`Villain_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ov_org_id`
    FOREIGN KEY (`org_id`)
    REFERENCES `SuperVillains`.`Orgs` (`org_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperVillains`.`Locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`Locations` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`Locations` (
  `loc_id` INT NOT NULL AUTO_INCREMENT,
  `Loc_Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NULL,
  `Address` VARCHAR(105) NOT NULL,
  `Latitude` FLOAT(30, 15) NULL,
  `longitude` FLOAT(30, 15) NULL,
  PRIMARY KEY (`loc_id`));


-- -----------------------------------------------------
-- Table `SuperVillains`.`Sightings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`Sightings` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`Sightings` (
  `sighting_id` INT NOT NULL AUTO_INCREMENT,
  `loc_id` INT NULL,
  `DateTime` DATETIME NOT NULL,
  PRIMARY KEY (`sighting_id`),
  CONSTRAINT `loc_id`
    FOREIGN KEY (`loc_id`)
    REFERENCES `SuperVillains`.`Locations` (`loc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperVillains`.`SightingsVillains`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperVillains`.`SightingsVillains` ;

CREATE TABLE IF NOT EXISTS `SuperVillains`.`SightingsVillains` (
  `sighting_id` INT NOT NULL,
  `Villain_id` INT NOT NULL,
  PRIMARY KEY (`sighting_id`, `Villain_id`),
  CONSTRAINT `sighting_id`
    FOREIGN KEY (`sighting_id`)
    REFERENCES `SuperVillains`.`Sightings` (`sighting_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vilian_id`
    FOREIGN KEY (`Villain_id`)
    REFERENCES `SuperVillains`.`SuperVillain` (`Villain_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

