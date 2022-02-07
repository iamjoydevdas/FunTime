package com.example.booking.dto.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.booking.dto.IBookingValidator;
import com.example.payload.Payload;
import com.example.status.BookingStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BookingValidatorRepo implements IBookingValidator {
	private final JdbcTemplate jdbcTemplate;

	@Value("${booking.prebooking.awit.time-in-minutes}")
	private Integer awitingTime;
	
	
	private static final String CHECK_BOOKING_WITH_AWAITING_TIME = "SELECT count(BOOKING_USER_ID) No FROM fun_time.booking WHERE TIME_OF_BOOKING >= CURRENT_TIMESTAMP - INTERVAL ? MINUTE AND BOOKING_USER_ID=? AND BOOKING_ID = ?";
	@Override
	public boolean isWaitingTimeExceeded(Payload payload) {
		Integer status = jdbcTemplate.queryForObject(CHECK_BOOKING_WITH_AWAITING_TIME, new Object[] {awitingTime, payload.getUserId(), payload.getBookingId()}, Integer.class);
		System.out.println(status);
		return status != 1;
	}

	
	private static final String CHECK_SEAT_BOOKED_WITH_OTHER_USER = "select count(screen_seat_id) no from  fun_time.show_seat s, booking b where screen_seat_id in " + 
			"(SELECT screen_seat_id FROM fun_time.show_seat where Booking_ID = ?) and SHOW_SEAT_STATUS = ?  " + 
			"and s.booking_id = b.booking_id and b.booking_user_id <> ?";
	
	@Override
	public boolean isSeatBookedWithOtherUsers(Payload payload) {
		Integer status = jdbcTemplate.queryForObject(CHECK_SEAT_BOOKED_WITH_OTHER_USER, new Object[] {payload.getBookingId(), BookingStatus.RESERVED, payload.getUserId()}, Integer.class);
		System.out.println(status);
		return status > 0;
	}
}
