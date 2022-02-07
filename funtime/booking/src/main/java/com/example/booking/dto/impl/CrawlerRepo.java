package com.example.booking.dto.impl;

import static com.example.status.BookingStatus.*;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.booking.dto.ICrawlerRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CrawlerRepo implements ICrawlerRepo {
	private final JdbcTemplate jdbcTemplate;
	
	@Value("${booking.prebooking.awit.time-in-minutes}")
	private Integer awitingTime;
	
	private static final String INVALIDED_EXCEEDED_AWAITING_BOOKINGS = "update booking b, show_seat s " + 
			"set booking_status = ?, SHOW_SEAT_STATUS = ? " + 
			"where s.booking_id = b.booking_id " + 
			"AND b.TIME_OF_BOOKING >= CURRENT_TIMESTAMP - INTERVAL ? MINUTE  "; 
	
	@Override
	public void invalidateExceededAwitedBookings() {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INVALIDED_EXCEEDED_AWAITING_BOOKINGS, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,TIME_EXCEED.toString());
			ps.setString(2, TIME_EXCEED.toString());
			ps.setLong(3, awitingTime);
			//ps.setLong(3, payload.getUserId());
			//ps.setLong(4, payload.getBookingId());
			return ps;
		});
	}

}
