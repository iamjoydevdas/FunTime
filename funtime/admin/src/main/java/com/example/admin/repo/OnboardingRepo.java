package com.example.admin.repo;

import static com.example.status.BookingStatus.PENDING;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.models.City;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class OnboardingRepo {
	private final JdbcTemplate jdbcTemplate;
	
	private static final String NEW_CITY = "INSERT INTO city (CITY_SHORT_NAME, CITY_NAME, CITY_STATE, CITY_PIN, FAV_CITY, AVTIVE) VALUES (?,?,?,?,?,true)";
	
	public Long addNewCity(City c) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(NEW_CITY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getShortName());
			ps.setString(2,c.getName());
			ps.setString(3, c.getState());
			ps.setLong(4, c.getPin());
			ps.setString(5, c.getFavCity());
			return ps;
		}, holder);

		Long bookingId = holder.getKey().longValue();
		return bookingId;
	}
}
