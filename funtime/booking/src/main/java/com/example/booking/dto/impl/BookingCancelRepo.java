package com.example.booking.dto.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.booking.dto.IBookingCancelRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class BookingCancelRepo implements IBookingCancelRepo {
	private final JdbcTemplate jdbcTemplate;
	
	private static final String CANCEL_BOOKING = "UPDATE `BOOKING` SET BOOKING_STATUS WHERE BOOKING_ID=?";
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void cancelBooking(Long bookingId) {
		jdbcTemplate.update(CANCEL_BOOKING, new Object[] {bookingId});
	}
	
}
