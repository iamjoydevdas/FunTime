package com.example.booking.dto.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.booking.dto.IBookingValidator;
import com.example.payload.Payload;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BookingValidatorRepo implements IBookingValidator {
	private final JdbcTemplate jdbcTemplate;

	@Value("${booking.prebooking.awit.time-in-minutes}")
	private Integer awitingTime;
	
	
	private static final String CHECK_BOOKING_WITH_AWAITING_TIME = "SELECT count(BOOKING_USER_ID) No FROM fun_time.booking WHERE TIME_OF_BOOKING >= CURRENT_TIMESTAMP - INTERVAL ? MINUTE AND BOOKING_USER_ID=? AND BOOKING_MOVIE_SHOW_TIME_ID = ?";
	@Override
	public boolean isWaitingTimeExceeded(Payload payload) {
		Integer status = jdbcTemplate.queryForObject(CHECK_BOOKING_WITH_AWAITING_TIME, new Object[] {awitingTime, payload.getUserId(), payload.getMovieShowTimeId()}, Integer.class);
		return status != 1;
	}

}
