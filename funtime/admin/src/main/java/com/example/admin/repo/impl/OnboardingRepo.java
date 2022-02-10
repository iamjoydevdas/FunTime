package com.example.admin.repo.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.admin.repo.IOnboardingRepo;
import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.Movie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class OnboardingRepo implements IOnboardingRepo {
	private final JdbcTemplate jdbcTemplate;
	
	private static final String NEW_CITY = "INSERT INTO city (CITY_SHORT_NAME, CITY_NAME, CITY_STATE, CITY_PIN, FAV_CITY, AVTIVE) VALUES (?,?,?,?,?,true)";
	private static final String NEW_CINEMA_HALL = "INSERT INTO `CINEMA_HALL` (`CINEMA_HALL_SHORT_NAME`,`CINEMA_HALL_NAME`,`CINEMA_HALL_CITY_ID`,`ACTIVE`, FAV_CINEMA_HALL) VALUES (?,?,?,TRUE,?)";
	private static final String NEW_MOVIE = "INSERT INTO `MOVIE` (MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, RELEASE_DATE, COUNTRY, GENERE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
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
	
	
	public Long addNewCinemaHall(CinemaHall ch) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(NEW_CINEMA_HALL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, ch.getCinemaHallShortName());
			ps.setString(2, ch.getCinemaHallName());
			ps.setLong(3, ch.getCityId());
			ps.setString(4, ch.getFavCity());
			return ps;
		}, holder);
		return holder.getKey().longValue();
	}
	
	public Long addMovie(Movie m) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(NEW_MOVIE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, m.getMovieTitle());
			ps.setString(2, m.getMovieDescription());
			ps.setString(3, m.getDuration());
			ps.setString(4, m.getLang());
			ps.setDate(5, Date.valueOf(m.getReleaseDate()));
			ps.setString(6, m.getCountry());
			ps.setString(7, m.getGenere());
			return ps;
		}, holder);
		return holder.getKey().longValue();
	}
}
