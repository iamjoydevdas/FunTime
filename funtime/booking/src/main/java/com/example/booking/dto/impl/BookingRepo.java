package com.example.booking.dto.impl;

import static com.example.status.BookingStatus.PENDING;
import static com.example.status.BookingStatus.RESEREVED_PAYMENT_AWAIT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.booking.dto.IBookingRepo;
import com.example.models.Booking;
import com.example.models.ScreenSeat;
import com.example.models.ShowSeat;
import com.example.payload.Payload;
import com.example.status.BookingStatus;
import com.example.status.SeatType;

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
	public void confirmBooking(Payload payload) {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(CONFIRM_BOOKING, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,RESEREVED_PAYMENT_AWAIT.toString());
			ps.setString(2, RESEREVED_PAYMENT_AWAIT.toString());
			ps.setLong(3, payload.getUserId());
			ps.setLong(4, payload.getBookingId());
			return ps;
		});
	}

	@Override
	public Booking bookingStatus(String bookingId) {
		Booking booking = getBookingStatus(bookingId);
		return booking;
	}
	
	
	private static final String BOOKING_STATUS = "SELECT BOOKING_ID, NO_OF_SEATS , TIME_OF_BOOKING , BOOKING_STATUS , TIME_OF_BOOKING_CONFIRMATION , ACTUAL_PRICE ,  DISCOUNT , " + 
			"DISCOUNTED_PRICE , TIME_OF_BOOKING_CANCEL , BOOKING_USER_ID , BOOKING_MOVIE_SHOW_TIME_ID FROM  BOOKING WHERE BOOKING_ID = ? ";
	private Booking getBookingStatus(String bookingId) {
		return jdbcTemplate.query(BOOKING_STATUS, new Object[] {bookingId}, new BookingMapper()).get(0);
	}
	
	private class BookingMapper implements RowMapper<Booking> {
		@Override
		public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return Booking.builder()
					.bookingId(rs.getLong("BOOKING_ID"))
					.bookingTimeStamp(rs.getTimestamp("Time_OF_BOOKING").toLocalDateTime())
					.status(BookingStatus.valueOf(rs.getString("BOOKING_STATUS")))
					.actualPrice(rs.getDouble("ACTUAL_PRICE"))
					.discount(rs.getInt("DISCOUNT"))
					.finallPrice(rs.getDouble("DISCOUNTED_PRICE"))
					.cancelTimeStamp(rs.getTimestamp("Time_OF_BOOKING").toLocalDateTime())
					.build();
		}
	}
	
	private static final String SEATS_IN_BOOKING = "SELECT SEAT_NUMBER, SEAT_TYPE FROM show_seat seat, screen_seat s_seat where booking_id=? " + 
			"and seat.screen_seat_id = s_seat.screen_seat_id";
	private List<ScreenSeat> getBookingSeats(String bookingId) {
		return jdbcTemplate.query(SEATS_IN_BOOKING, new Object[] {bookingId}, new ScreenSeatMapper());
	}
	
	private class ScreenSeatMapper implements RowMapper<ScreenSeat> {
		@Override
		public ScreenSeat mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return ScreenSeat.builder()
					.seatNo(rs.getString("SEAT_NUMBER"))
					.seatType(SeatType.valueOf(rs.getString("SEAT_TYPE")))
					.build();
		}
	}
}
