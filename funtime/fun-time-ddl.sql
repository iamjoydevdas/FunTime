DROP DATABASE IF EXISTS `FUN_TIME`;

CREATE DATABASE IF NOT EXISTS `FUN_TIME`;

USE `FUN_TIME`;

CREATE TABLE CITY (
	CITY_ID INT(4) NOT NULL AUTO_INCREMENT,
    CITY_SHORT_NAME VARCHAR(4) NOT NULL,
	CITY_NAME VARCHAR(20) NOT NULL,
	CITY_STATE VARCHAR(20) NOT NULL,
	CITY_PIN INT(6) NOT NULL,
    FAV_CITY VARCHAR(1) NOT NULL,
	AVTIVE BOOLEAN NOT NULL,
	CONSTRAINT PK_CITY_ID PRIMARY KEY (CITY_ID),
    CONSTRAINT UK_CITY_SHORT_NAME UNIQUE  (CITY_SHORT_NAME)
) ENGINE=InnoDB;

ALTER TABLE CITY AUTO_INCREMENT = 1;

CREATE TABLE CINEMA_HALL (
	CINEMA_HALL_ID INT(4) NOT NULL AUTO_INCREMENT,
    CINEMA_HALL_SHORT_NAME VARCHAR(6) NOT NULL,
	CINEMA_HALL_NAME VARCHAR(64) NOT NULL,
	CINEMA_HALL_SCREENS INT(2) NOT NULL,
    CINEMA_HALL_CITY_ID INT(4) NOT NULL,
    ACTIVE BOOLEAN NOT NULL,
	CONSTRAINT PK_CINEMA_HALL_ID PRIMARY KEY (CINEMA_HALL_ID),
	CONSTRAINT UK_CINEMA_HALL_SHORT_NAME UNIQUE  ( CINEMA_HALL_SHORT_NAME),
  	CONSTRAINT FK_CINEMA_HALL_CITY_ID FOREIGN KEY (CINEMA_HALL_CITY_ID) REFERENCES CITY (CITY_ID)
) ENGINE=InnoDB;

ALTER TABLE CINEMA_HALL AUTO_INCREMENT = 1;

CREATE TABLE MOVIE (
	MOVIE_ID INT(4) NOT NULL AUTO_INCREMENT,
	MOVIE_TITLE VARCHAR(100) NOT NULL,
	MOVIE_DESCRIPTION VARCHAR(400) NOT NULL,
	DURATION VARCHAR(10) NOT NULL,
    LANG VARCHAR(16) NOT NULL,
    RELEASE_DATE DATETIME,
    COUNTRY VARCHAR(64) NOT NULL,
    GENERE VARCHAR(20) NOT NULL,
	CONSTRAINT PK_MOVIE_ID PRIMARY KEY (MOVIE_ID)
) ENGINE=InnoDB;

ALTER TABLE MOVIE AUTO_INCREMENT = 1;

CREATE TABLE SCREEN (
	SCREEN_ID INT(4) NOT NULL AUTO_INCREMENT,
    SCREEN_NAME VARCHAR(64) NOT NULL,
    TOTAL_SEATS INT(4) NULL NULL,
    SCREEN_CINEMA_HALL_ID INT(4) NOT NULL,
    CONSTRAINT PK_SCREEN_ID PRIMARY KEY (SCREEN_ID),
    CONSTRAINT FK_SCREEN_CINEMA_HALL_ID FOREIGN KEY (SCREEN_CINEMA_HALL_ID) REFERENCES CINEMA_HALL (CINEMA_HALL_ID)
) ENGINE=InnoDB;

ALTER TABLE SCREEN AUTO_INCREMENT = 1;


CREATE TABLE SCREEN_SEAT (
	SCREEN_SEAT_ID INT(4) NOT NULL AUTO_INCREMENT,
    SEAT_NUMBER VARCHAR(64) NOT NULL,
    SEAT_TYPE VARCHAR(1) NULL NULL,
    SCREEN_CINEMA_HALL_SEAT_ID INT(4) NOT NULL,
    PRICE VARCHAR(10) NOT NULL,
    CONSTRAINT PK_SCREEN_SEAT_ID PRIMARY KEY (SCREEN_SEAT_ID),
    CONSTRAINT FK_SCREEN_CINEMA_HALL_SEAT_ID FOREIGN KEY (SCREEN_CINEMA_HALL_SEAT_ID) REFERENCES SCREEN (SCREEN_ID)
) ENGINE=InnoDB;

ALTER TABLE SCREEN_SEAT AUTO_INCREMENT = 1;


CREATE TABLE MOVIE_SHOW_TIME (
	MOVIE_SHOW_TIME_ID INT(4) NOT NULL AUTO_INCREMENT,
    MOVIE_SHOW_TIME_DATE DATETIME NOT NULL,
    MOVIE_SHOW_TIME_STARTTIME VARCHAR(10) NULL NULL,
    MOVIE_SHOW_TIME_ENDTIME VARCHAR(10) NOT NULL,
    MOVIE_SHOW_TIME_SCREEN_ID INT(4) NOT NULL,
    MOVIE_SHOW_TIME_MOVIE_ID INT(4) NOT NULL,
    CONSTRAINT PK_MOVIE_SHOW_TIME_ID PRIMARY KEY (MOVIE_SHOW_TIME_ID),
    CONSTRAINT FK_MOVIE_SHOW_TIME_SCREEN_ID FOREIGN KEY (MOVIE_SHOW_TIME_SCREEN_ID) REFERENCES SCREEN (SCREEN_ID),
    CONSTRAINT FK_MOVIE_SHOW_TIME_MOVIE_ID FOREIGN KEY (MOVIE_SHOW_TIME_MOVIE_ID) REFERENCES MOVIE (MOVIE_ID)
) ENGINE=InnoDB;

ALTER TABLE MOVIE_SHOW_TIME AUTO_INCREMENT = 1;


CREATE TABLE USERS (
	USERS_ID INT(4) NOT NULL AUTO_INCREMENT,
    USERS_NAME VARCHAR(64) NOT NULL,
    USERS_PASSWORD VARCHAR(64) NULL NULL,
    USERS_EMAIL VARCHAR(64) NOT NULL,
    USERS_PHONE INT(10) NOT NULL,
    CONSTRAINT PK_USERS_ID PRIMARY KEY (USERS_ID)
) ENGINE=InnoDB;

ALTER TABLE USERS AUTO_INCREMENT = 1;


CREATE TABLE BOOKING (
	BOOKING_ID INT(4) NOT NULL AUTO_INCREMENT,
    NO_OF_SEATS INT(2) NOT NULL,
    TIME_OF_BOOKING DATETIME NULL NULL,
    BOOKING_STATUS VARCHAR(2) NOT NULL,
   /* BOOKING_USER_ID INT(4) NOT NULL,*/
    BOOKING_MOVIE_SHOW_TIME_ID INT(4) NOT NULL,
    CONSTRAINT PK_BOOKING_ID PRIMARY KEY (BOOKING_ID),
 /*   CONSTRAINT FK_BOOKING_USER_ID FOREIGN KEY (BOOKING_USER_ID) REFERENCES USERS (USERS_ID),*/
    CONSTRAINT FK_BOOKING_MOVIE_SHOW_TIME_ID FOREIGN KEY (BOOKING_MOVIE_SHOW_TIME_ID) REFERENCES MOVIE_SHOW_TIME (MOVIE_SHOW_TIME_ID)
) ENGINE=InnoDB;

ALTER TABLE BOOKING AUTO_INCREMENT = 1;


CREATE TABLE SHOW_SEAT (
	SHOW_SEAT_ID INT(4) NOT NULL AUTO_INCREMENT,
    SHOW_SEAT_STATUS VARCHAR(1) NOT NULL,
    SCREEN_SEAT_ID INT(4) NOT NULL,
    MOVIE_SHOW_TIME_ID INT(4) NOT NULL,
    BOOKING_ID INT(4) NOT NULL,
    CONSTRAINT PK_SHOW_SEAT_ID PRIMARY KEY (SHOW_SEAT_ID),
    CONSTRAINT FK_SCREEN_SEAT_ID FOREIGN KEY (SCREEN_SEAT_ID) REFERENCES SCREEN_SEAT (SCREEN_SEAT_ID),
    CONSTRAINT FK_MOVIE_SHOW_TIME_ID FOREIGN KEY (MOVIE_SHOW_TIME_ID) REFERENCES MOVIE_SHOW_TIME (MOVIE_SHOW_TIME_ID),
    CONSTRAINT FK_BOOKING_ID FOREIGN KEY (BOOKING_ID) REFERENCES BOOKING (BOOKING_ID)
) ENGINE=InnoDB;

ALTER TABLE SHOW_SEAT AUTO_INCREMENT = 1;

CREATE TABLE PAYMENT (
	PAYMENT_ID INT(4) NOT NULL AUTO_INCREMENT,
    AMOUNT DECIMAL(4,2) NOT NULL,
    PAYMENT_TIME DATETIME NULL NULL,
    DISCOUNT_COUPON_ID INT(4) NOT NULL,
    TRANSACTION_ID INT(4) NOT NULL,
    PAYMENT_METHOD INT(4) NOT NULL,
    PAYMENT_BOOKING_ID INT(4) NOT NULL,
    CONSTRAINT PK_PAYMENT_ID PRIMARY KEY (PAYMENT_ID),
    CONSTRAINT FK_PAYMENT_BOOKING_ID FOREIGN KEY (PAYMENT_BOOKING_ID) REFERENCES BOOKING (BOOKING_ID)
) ENGINE=InnoDB;

ALTER TABLE PAYMENT AUTO_INCREMENT = 1;


CREATE TABLE SEAT_TYPE (
	SEAT_TYPE_ID INT(4) NOT NULL AUTO_INCREMENT,
    SEAT_TYPE_NAME VARCHAR(1) NOT NULL,
    SEAT_TYPE_DESC VARCHAR(50) NOT NULL,
    CONSTRAINT PK_SEAT_TYPE_ID PRIMARY KEY (SEAT_TYPE_ID)
) ENGINE=InnoDB;

ALTER TABLE SEAT_TYPE AUTO_INCREMENT = 1;

CREATE TABLE BOOKING_STATUS (
	BOOKING_STATUS_ID INT(4) NOT NULL AUTO_INCREMENT,
    BOOKING_STATUS_NAME VARCHAR(1) NOT NULL,
    BOOKING_STATUS_DESC VARCHAR(50) NOT NULL,
    CONSTRAINT PK_BOOKING_STATUS_ID PRIMARY KEY (BOOKING_STATUS_ID)
) ENGINE=InnoDB;

ALTER TABLE BOOKING_STATUS AUTO_INCREMENT = 1;
