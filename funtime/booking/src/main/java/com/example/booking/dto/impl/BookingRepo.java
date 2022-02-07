package com.example.booking.dto.impl;

import static com.example.status.BookingStatus.PENDING;
import static com.example.status.BookingStatus.RESEREVED_PAYMENT_AWAIT;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.booking.dto.IBookingRepo;
import com.example.payload.Payload;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookingRepo implements IBookingRepo {
	private final JdbcTemplate jdbcTemplate;
	
	private static final String INITIATE_BOOKING = "INSERT INTO `BOOKING` (NO_OF_SEATS, TIME_OF_BOOKING, BOOKING_STATUS, BOOKING_MOVIE_SHOW_TIME_ID, BOOKING_USER_ID) VALUES(?, now(), ?, ?, ?)";
	private static final String INITIATE_SEAT_BOOKING = "INSERT INTO `SHOW_SEAT` (SHOW_SEAT_STATUS, SCREEN_SEAT_ID, MOVIE_SHOW_TIME_ID, BOOKING_ID) VALUES(?, ?, ?, ?)";

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String checkout(Payload payload) {
		
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INITIATE_BOOKING, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 1);
			ps.setString(2, PENDING.toString());
			ps.setLong(3, payload.getMovieShowTimeId());
			ps.setLong(4, payload.getUserId());
			return ps;
		}, holder);

		Long bookingId = holder.getKey().longValue();

		System.out.println(bookingId);
		
		jdbcTemplate.batchUpdate(INITIATE_SEAT_BOOKING, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				ps.setString(1, PENDING.toString());
				ps.setLong(2, payload.getSeats().get(i));
				ps.setLong(3, payload.getMovieShowTimeId());
				ps.setLong(4, bookingId);
			}

			@Override
			public int getBatchSize() {
				return payload.getSeats().size();
			}
		});
		return String.valueOf(bookingId);
	}

	private static final String CONFIRM_BOOKING = "update booking b, show_seat s " + 
			"set booking_status = ?, SHOW_SEAT_STATUS = ? " + 
			"where BOOKING_USER_ID= ? AND b.BOOKING_ID = ? " + 
			"and  s.booking_id = b.booking_id";
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String confirmBooking(Payload payload) {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(CONFIRM_BOOKING, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,RESEREVED_PAYMENT_AWAIT.toString());
			ps.setString(2, RESEREVED_PAYMENT_AWAIT.toString());
			ps.setLong(3, payload.getUserId());
			ps.setLong(4, payload.getBookingId());
			return ps;
		});
		return null;
	}
	
	

}
