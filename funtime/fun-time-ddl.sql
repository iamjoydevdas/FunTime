DROP DATABASE IF EXISTS `fun-time`;

CREATE DATABASE IF NOT EXISTS `fun-time`;

USE `fun-time`;

CREATE TABLE city (
	cityId INT(4) NOT NULL AUTO_INCREMENT,
    cityNM varchar(4) NOT NULL,
	cityName VARCHAR(20) NOT NULL,
	favCity VARCHAR(1) NOT NULL,
	cityActive boolean NOT NULL,
    state VARCHAR(20) NOT NULL,
    pin INT(7) NOT NULL,
	CONSTRAINT pk_cityId PRIMARY KEY (cityId),
    CONSTRAINT uk_cityId UNIQUE  (cityNM)
) ENGINE=InnoDB;

ALTER TABLE city AUTO_INCREMENT = 1;

INSERT INTO `city`(`cityNM`,`cityName`,`favCity`,`cityActive`,`state`,`pin`)VALUES("KOLK","Kolkata","Y",true,"West Bengal","700001");
INSERT INTO `city`(`cityNM`,`cityName`,`favCity`,`cityActive`,`state`,`pin`)VALUES("DELH","Delhi","Y",true,"West Bengal","700001");
INSERT INTO `city`(`cityNM`,`cityName`,`favCity`,`cityActive`,`state`,`pin`)VALUES("MUMB","Mumbai","Y",true,"West Bengal","700001");
INSERT INTO `city`(`cityNM`,`cityName`,`favCity`,`cityActive`,`state`,`pin`)VALUES("SONR","Sonarpur","N",true,"West Bengal","700001");
