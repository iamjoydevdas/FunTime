USE `FUN_TIME`;


/* Insert */
INSERT INTO `SEAT_TYPE` (`SEAT_TYPE_NAME`,`SEAT_TYPE_DESC`) VALUES ('P','Preminum');
INSERT INTO `SEAT_TYPE` (`SEAT_TYPE_NAME`,`SEAT_TYPE_DESC`) VALUES ('R','Regular');
INSERT INTO `SEAT_TYPE` (`SEAT_TYPE_NAME`,`SEAT_TYPE_DESC`) VALUES ('E','Executive');

INSERT INTO `BOOKING_STATUS` (`BOOKING_STATUS_NAME`,`BOOKING_STATUS_DESC`) VALUES ('F','FAILED');
INSERT INTO `BOOKING_STATUS` (`BOOKING_STATUS_NAME`,`BOOKING_STATUS_DESC`) VALUES ('B','BOOKED');
INSERT INTO `BOOKING_STATUS` (`BOOKING_STATUS_NAME`,`BOOKING_STATUS_DESC`) VALUES ('C','CANCEL');



INSERT INTO `city` (`CITY_SHORT_NAME`,`CITY_NAME`,`CITY_STATE`,`CITY_PIN`,`FAV_CITY`,`AVTIVE`) VALUES ('KOLK','KOLKATA','WEST BENGAL',700150, 'Y', TRUE);
INSERT INTO `city` (`CITY_SHORT_NAME`,`CITY_NAME`,`CITY_STATE`,`CITY_PIN`,`FAV_CITY`,`AVTIVE`) VALUES ('MUMB','MUMBAI','MAHARASTRA',400150, 'Y', TRUE);
INSERT INTO `city` (`CITY_SHORT_NAME`,`CITY_NAME`,`CITY_STATE`,`CITY_PIN`,`FAV_CITY`,`AVTIVE`) VALUES ('DELH','DELHI','DELHI',400150, 'Y', TRUE);
INSERT INTO `city` (`CITY_SHORT_NAME`,`CITY_NAME`,`CITY_STATE`,`CITY_PIN`,`FAV_CITY`,`AVTIVE`) VALUES ('BRPR','BARUIPUR','WEST BENGAL',700150, 'Y', TRUE);
INSERT INTO `city` (`CITY_SHORT_NAME`,`CITY_NAME`,`CITY_STATE`,`CITY_PIN`,`FAV_CITY`,`AVTIVE`) VALUES ('SNPR','SONARPUR','WEST BENGAL',700150, 'Y', TRUE);


INSERT INTO `CINEMA_HALL` (`CINEMA_HALL_SHORT_NAME`,`CINEMA_HALL_NAME`,`CINEMA_HALL_SCREENS`,`CINEMA_HALL_CITY_ID`,`ACTIVE`, FAV_CINEMA_HALL) VALUES ('SVFC1', 'SVF Cinemas',4,1, TRUE, 'Y');
INSERT INTO `CINEMA_HALL` (`CINEMA_HALL_SHORT_NAME`,`CINEMA_HALL_NAME`,`CINEMA_HALL_SCREENS`,`CINEMA_HALL_CITY_ID`,`ACTIVE`, FAV_CINEMA_HALL) VALUES ('BIGC1', 'Big Cinemas',2,1, TRUE, 'Y');
INSERT INTO `CINEMA_HALL` (`CINEMA_HALL_SHORT_NAME`,`CINEMA_HALL_NAME`,`CINEMA_HALL_SCREENS`,`CINEMA_HALL_CITY_ID`,`ACTIVE`, FAV_CINEMA_HALL) VALUES ('HUMAC', 'Huma Cinema',1,2, TRUE, 'Y');

INSERT INTO `MOVIE`  (MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, RELEASE_DATE, COUNTRY, GENERE) VALUES ("YE KALI KALI ANKHE", "NETFLIX MOVIE", "2.30 HRS", "HINDI", "2022-01-03", "INDIA", "SUSPENSE"); 
INSERT INTO `MOVIE`  (MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, RELEASE_DATE, COUNTRY, GENERE) VALUES ("RANG DE BASANTI", "AMRIR KHAN MOVIE", "2.30 HRS", "HINDI", "2016-01-03", "INDIA", "SUSPENSE"); 

INSERT INTO `SCREEN`  (SCREEN_NAME, TOTAL_SEATS, SCREEN_CINEMA_HALL_ID) VALUES ("Screen 1", 60, 1); 
INSERT INTO `SCREEN`  (SCREEN_NAME, TOTAL_SEATS, SCREEN_CINEMA_HALL_ID) VALUES ("Screen 2", 48, 1); 
INSERT INTO `SCREEN`  (SCREEN_NAME, TOTAL_SEATS, SCREEN_CINEMA_HALL_ID) VALUES ("Screen 3", 58, 1); 
INSERT INTO `SCREEN`  (SCREEN_NAME, TOTAL_SEATS, SCREEN_CINEMA_HALL_ID) VALUES ("Screen 4", 64, 1); 

INSERT INTO `SCREEN`  (SCREEN_NAME, TOTAL_SEATS, SCREEN_CINEMA_HALL_ID) VALUES ("Screen 1", 60, 2); 
INSERT INTO `SCREEN`  (SCREEN_NAME, TOTAL_SEATS, SCREEN_CINEMA_HALL_ID) VALUES ("Screen 2", 48, 2); 


INSERT INTO `MOVIE_SHOW_TIME`  (MOVIE_SHOW_TIME_DATE, MOVIE_SHOW_TIME_STARTTIME, MOVIE_SHOW_TIME_ENDTIME, MOVIE_SHOW_TIME_SCREEN_ID, MOVIE_SHOW_TIME_MOVIE_ID) VALUES(now(), 10.00, 12.00, 1, 1);
INSERT INTO `MOVIE_SHOW_TIME`  (MOVIE_SHOW_TIME_DATE, MOVIE_SHOW_TIME_STARTTIME, MOVIE_SHOW_TIME_ENDTIME, MOVIE_SHOW_TIME_SCREEN_ID, MOVIE_SHOW_TIME_MOVIE_ID) VALUES(now(), 9.00, 11.30, 1, 1);
INSERT INTO `MOVIE_SHOW_TIME`  (MOVIE_SHOW_TIME_DATE, MOVIE_SHOW_TIME_STARTTIME, MOVIE_SHOW_TIME_ENDTIME, MOVIE_SHOW_TIME_SCREEN_ID, MOVIE_SHOW_TIME_MOVIE_ID) VALUES(now(), 10.30, 13.00, 2, 2);

INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A1", 1, "100", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A2", 1, "100", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A3", 1, "100", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A4", 1, "100", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A5", 1, "100", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A6", 1, "100", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A7", 1, "100", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B1", 2, "200", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B2", 2, "200", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B3", 2, "200", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B4", 2, "200", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("C1", 3, "300", 1);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("C2", 3, "300", 1);

INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A2", 1, "100", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A2", 1, "100",2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A3", 1, "100", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A4", 1, "100", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A5", 1, "100", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A6", 1, "100", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("A7", 1, "100", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B2", 2, "200", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B2", 2, "200", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B3", 2, "200", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("B4", 2, "200", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("C2", 3, "300", 2);
INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES("C2", 3, "300", 2);

INSERT INTO `USERS`(`USERS_NAME`,`USERS_PASSWORD`,`USERS_EMAIL`,`USERS_PHONE`) VALUES("Joydev","1","iamjoy@gmail.com", 233);
INSERT INTO `USERS`(`USERS_NAME`,`USERS_PASSWORD`,`USERS_EMAIL`,`USERS_PHONE`) VALUES("Swarup","1","iamjoy@gmail.com", 233);

INSERT INTO `BOOKING` (NO_OF_SEATS, TIME_OF_BOOKING, BOOKING_STATUS, BOOKING_MOVIE_SHOW_TIME_ID, BOOKING_USER_ID) VALUES(3, now(), 1, 1, 1);

INSERT INTO `SHOW_SEAT` (SHOW_SEAT_STATUS, SCREEN_SEAT_ID, MOVIE_SHOW_TIME_ID, BOOKING_ID) VALUES(1, 1, 3, 1);
INSERT INTO `SHOW_SEAT` (SHOW_SEAT_STATUS, SCREEN_SEAT_ID, MOVIE_SHOW_TIME_ID, BOOKING_ID) VALUES(1, 2, 3, 1);
INSERT INTO `SHOW_SEAT` (SHOW_SEAT_STATUS, SCREEN_SEAT_ID, MOVIE_SHOW_TIME_ID, BOOKING_ID) VALUES(1, 3, 3, 1);


INSERT INTO `BOOKING` (NO_OF_SEATS, TIME_OF_BOOKING, BOOKING_STATUS, BOOKING_MOVIE_SHOW_TIME_ID, BOOKING_USER_ID) VALUES(3, now(), 1, 1, 1);
