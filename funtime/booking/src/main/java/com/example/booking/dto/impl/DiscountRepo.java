package com.example.booking.dto.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.booking.dto.IDiscountRepo;
import com.example.booking.utils.BookingUtils;
import com.example.discount.model.DiscountEligible;
import com.example.status.BookingStatus;
import com.example.status.DayShowNames;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class DiscountRepo implements IDiscountRepo {
	private final JdbcTemplate jdbcTemplate;

	private static final String GET_PRE_DISCOUNT_DETAILS = "select c.FAV_CITY, ch.FAV_CINEMA_HALL, cnt.total_seats, actualPrice, " + 
			"CASE WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 9.00 AND 12.00 THEN \"Morning\" " + 
			"              WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 12.01 AND 18.00 THEN \"Afternoon\" " + 
			"              WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 18.00 AND 24.00 THEN \"Evening\" " + 
			"         END as period " + 
			" from  " + 
			"( " + 
			"	select total_seats, a.BOOKING_MOVIE_SHOW_TIME_ID, a.booking_id, sum(ss.price) actualPrice from  " + 
			"	(select count(show_seat_id) as total_seats, b.BOOKING_MOVIE_SHOW_TIME_ID, b.booking_id from BOOKING b, show_seat s " + 
			"	where b.booking_id=1 and b.booking_id=s.booking_Id) a, show_seat s, screen_seat ss " + 
			"	where a.booking_id=s.booking_id " + 
			"	and s.screen_seat_id = ss.SCREEN_SEAT_ID " + 
			"     " + 
			"    ) cnt, " + 
			"MOVIE_SHOW_TIME mst, " + 
			"screen s, CINEMA_HALL ch, city c " + 
			"where cnt.BOOKING_MOVIE_SHOW_TIME_ID = mst.MOVIE_SHOW_TIME_ID " + 
			"and mst.MOVIE_SHOW_TIME_SCREEN_ID = s.screen_id " + 
			"and s.SCREEN_CINEMA_HALL_ID = ch.CINEMA_HALL_ID " + 
			"and ch.CINEMA_HALL_CITY_ID = c.city_id";

	@Override
	public DiscountEligible getEligibleDiscount(Long bookingId) {
		return jdbcTemplate.query(GET_PRE_DISCOUNT_DETAILS, new Object[] {bookingId}, new DiscountEligibleMapper())
				.get(0);
	}
	
	private class DiscountEligibleMapper implements RowMapper<DiscountEligible> {
		@Override
		public DiscountEligible mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return DiscountEligible.builder()
					.actualPrice(rs.getDouble("actualPrice"))
					.discountEligibleAfterNoonShow(DayShowNames.AFTERNOON.equals(DayShowNames.valueOf(rs.getString("period"))))
					.discountEligibleCinemaHall("Y".equalsIgnoreCase(rs.getString("FAV_CINEMA_HALL")))
					.discountEligibleCinemaHall("Y".equalsIgnoreCase(rs.getString("FAV_CITY")))
					.noOfTickets(rs.getLong("total_seats"))
					.build();
		}
	}

	
	
	private static final String UPDATE_DISCOUNT_DETAILS = "update BOOKING " + 
																	"SET ACTUAL_PRICE = ?, " + 
																	"DISCOUNT_DESC = ?, " + 
																	"DISCOUNTED_PRICE = ?, " + 
																	"BOOKING_STATUS=? " + 
																	"where booking_id=?";
	@Override
	public void updatePrice(Long bookingId, DiscountEligible discountEligible) {
		jdbcTemplate.update(UPDATE_DISCOUNT_DETAILS, new Object[] {discountEligible.getActualPrice(), discountEligible.getDiscountDesc(), 
				BookingUtils.getFinalAmount(discountEligible), BookingStatus.RESEREVED_PAYMENT_AWAIT, bookingId});
	}

}
