package com.example.crawler.repo.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.User;
import com.example.crawler.repo.ICrawlerRepo;
import com.example.status.BookingStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CrawlerRepo implements ICrawlerRepo {
	private final JdbcTemplate jdbcTemplate;
	
	@Value("${booking.prebooking.awit.time-in-seconds}")
	private Integer awitingTime;
	
	private static final String FETCH_EXCEEDED_AWAITING_BOOKINGS = "select booking_id from booking where UNIX_TIMESTAMP(now()) - UNIX_TIMESTAMP(time_of_booking) > ?"; 
	
	private static final String INVALIDED_EXCEEDED_AWAITING_BOOKINGS = "update booking b, show_seat s " + 
																		"set booking_status = ?, SHOW_SEAT_STATUS = ?  " + 
																		"where b.booking_id = s.booking_id and booking_status = 'PENDING' and b.booking_id=?";
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void invalidateExceededAwitedBookings() {
		System.out.println(awitingTime);
		List<Long> exceededBookingIds = jdbcTemplate.query(FETCH_EXCEEDED_AWAITING_BOOKINGS, new Object[] {awitingTime}, new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong(1);
			}
			
		});
		
		jdbcTemplate.batchUpdate(INVALIDED_EXCEEDED_AWAITING_BOOKINGS, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) 
				throws SQLException {
            	ps.setString(1, BookingStatus.TIME_EXCEED.toString());
            	ps.setString(2, BookingStatus.TIME_EXCEED.toString());
                ps.setLong(3, exceededBookingIds.get(i));
            }

            public int getBatchSize() {
                return exceededBookingIds.size();
            }

        });
		
	}

	@Override
	public List<User> getAllUsersToNotify() {
		// Return USERS with Reserved status
		return null;
	}

	@Override
	public void updateNotificationStatus(Long bookingId) {
		// Notificationa status in booking table
		//Also, make a entry in NOTIFICATION table with time stamp
		
	}

}
