USE `FUN_TIME`;

CREATE TABLE BOOKING_AUDIT (
	BOOKING_AUDIT_ID INT(4) NOT NULL AUTO_INCREMENT,
    BOOKING_ID INT(4) NOT NULL ,
    NO_OF_SEATS INT(2) NOT NULL,
    TIME_OF_BOOKING DATETIME NOT NULL,
    BOOKING_STATUS ENUM("PENDING", "RESERVED", "CANCELLED", "FAILED", "TIME_EXCEED", "RESEREVED_PAYMENT_AWAIT", "CANCELLED_PAYMENT_FAILURE"),
    TIME_OF_BOOKING_CONFIRMATION DATETIME,
    ACTUAL_PRICE DOUBLE(5,2),
    DISCOUNT_DESC VARCHAR(100),
    DISCOUNTED_PRICE DOUBLE(5,2),
    TIME_OF_BOOKING_CANCEL DATETIME,
    BOOKING_USER_ID INT(4) NOT NULL,
    BOOKING_MOVIE_SHOW_TIME_ID INT(4) NOT NULL,
    BOOKING_AUDIT_TS DATETIME,
    CONSTRAINT PK_BOOKING_AUDIT_ID PRIMARY KEY (BOOKING_AUDIT_ID)
) ENGINE=InnoDB;

ALTER TABLE BOOKING_AUDIT AUTO_INCREMENT = 1;