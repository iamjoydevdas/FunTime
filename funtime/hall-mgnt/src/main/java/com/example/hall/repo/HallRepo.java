package com.example.hall.repo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Screen;
import com.example.models.ScreenSeat;
import com.example.models.ShowTime;
import com.example.status.BookingStatus;
import com.example.status.SeatType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class HallRepo {
	private final JdbcTemplate jdbcTemplate;
	
	private static final String ADD_SCREEN_TO_CINEMA_HALL = "INSERT INTO `SCREEN`  (SCREEN_NAME, SCREEN_CINEMA_HALL_ID) VALUES (?, ?); ";
	private static final String ADD_SEATS_TO_CINEMA_HALL_SCREEN = "INSERT INTO `SCREEN_SEAT`  (SEAT_NUMBER, SEAT_TYPE, PRICE, SCREEN_CINEMA_HALL_SEAT_ID) VALUES(?, ?, ?, ?);";
	
	public Long addScreenToCinemaHall(Long cinemaHallId, Screen s) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_SCREEN_TO_CINEMA_HALL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, s.getScreenName());
			ps.setLong(2, cinemaHallId);
			return ps;
		}, holder);

		Long bookingId = holder.getKey().longValue();
		return bookingId;
	}
	
	public void addSeatToScreenToCinemaHall(Long screenId,  List<ScreenSeat> seat) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.batchUpdate(ADD_SEATS_TO_CINEMA_HALL_SCREEN, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) 
				throws SQLException {
            	ps.setString(1, seat.get(i).getSeatNo());
            	ps.setString(2, seat.get(i).getSeatType().toString());
            	ps.setString(2, seat.get(i).getPrice());
                ps.setLong(3, screenId);
            }

            public int getBatchSize() {
                return seat.size();
            }

        });
	}
	
	private static final String ADD_NEW_SHOW = "INSERT INTO `MOVIE_SHOW_TIME`  (MOVIE_SHOW_TIME_DATE, MOVIE_SHOW_TIME_STARTTIME, MOVIE_SHOW_TIME_ENDTIME, MOVIE_SHOW_TIME_SCREEN_ID, MOVIE_SHOW_TIME_MOVIE_ID)"
			+ " VALUES(?, ?, ?, ?, ?);";
	
	public Long addMovieShowTimeToScreenToCinemaHall(Long screenId, Long movieId, ShowTime s) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_NEW_SHOW, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Date.valueOf(s.getMovieShowDate()));
			ps.setDouble(2, s.getMovieShowStartTime());
			ps.setDouble(3, s.getMovieShowEndTime());
			ps.setLong(4, screenId);
			ps.setLong(5, movieId);
			return ps;
		}, holder);

		return holder.getKey().longValue();
	}
	/*
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
	} */
}
