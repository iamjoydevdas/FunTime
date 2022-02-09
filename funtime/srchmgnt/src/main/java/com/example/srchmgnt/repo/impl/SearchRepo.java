package com.example.srchmgnt.repo.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.GenericSearch;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.models.ScreenSeat;
import com.example.models.ShowSeat;
import com.example.models.ShowTime;
import com.example.payload.Payload;
import com.example.srchmgnt.repo.ISearchRepo;
import com.example.status.SeatType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class SearchRepo implements ISearchRepo {
	private final ObjectMapper mapper;
	
	private final String ALL_CITY = "SELECT CITY_ID, CITY_SHORT_NAME, CITY_NAME, CITY_STATE, CITY_PIN, FAV_CITY, AVTIVE FROM CITY;";
	
	private final String MOVIES_BY_CITY = "SELECT MOVIE_ID, MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, GENERE, COUNTRY, RELEASE_DATE " + 
			" FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M " + 
			"WHERE C.CITY_SHORT_NAME = ? AND C.CITY_ID = CH.CINEMA_HALL_CITY_ID " + 
			"AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID " + 
			"AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID " + 
			"AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID and DATE(MOVIE_SHOW_TIME_DATE) = ?";
	
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public List<City> getAllCities() {
		List<City> cities = new ArrayList<>();
		jdbcTemplate.query(ALL_CITY, (rs)-> {
			City c = City.builder()
						.name(rs.getString("CITY_NAME"))
						.shortName(rs.getString("CITY_SHORT_NAME"))
						.state(rs.getString("CITY_STATE"))
						.favCity(rs.getString("FAV_CITY"))
						.pin(rs.getLong("CITY_PIN"))
						.build();
			
			cities.add(c);
		});
		return cities;
	}

	@Override
	public List<Movie> getMoviesByCity(String city, LocalDate date) {
		return jdbcTemplate.query(MOVIES_BY_CITY, new Object[] { city, date.toString() } , new MovieRowMapper());
	}
	
	private class MovieRowMapper implements RowMapper<Movie> {
		@Override
		public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return Movie.builder().country(rs.getString("COUNTRY"))
					.duration(rs.getString("DURATION"))
					.genere(rs.getString("GENERE"))
					.movieDescription(rs.getString("MOVIE_DESCRIPTION"))
					.movieTitle(rs.getString("MOVIE_TITLE"))
					.lang(rs.getString("LANG"))
					.movieId(rs.getLong("MOVIE_ID"))
					.releaseDate(rs.getDate("RELEASE_DATE").toLocalDate())
					.build();
		}
	}
	
	private Movie getMovies(ResultSet rs) throws SQLException {
		return Movie.builder().country(rs.getString("COUNTRY"))
				.duration(rs.getString("DURATION"))
				.genere(rs.getString("GENERE"))
				.movieDescription(rs.getString("MOVIE_DESCRIPTION"))
				.movieTitle(rs.getString("MOVIE_TITLE"))
				.lang(rs.getString("LANG"))
				.movieId(rs.getLong("MOVIE_ID"))
				.releaseDate(rs.getDate("RELEASE_DATE").toLocalDate())
				.build();
	}
	
	private final String CINEMA_HALLS_BY_CITY_MOVIE = "SELECT  CINEMA_HALL_ID, CINEMA_HALL_SHORT_NAME, CINEMA_HALL_NAME, MOVIE_SHOW_TIME_STARTTIME, SCREEN_NAME, SCREEN_ID, MOVIE_SHOW_TIME_ID " + 
			" FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M " + 
			"WHERE  " + 
			"C.CITY_SHORT_NAME = ? AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID " + 
			"AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID " + 
			"AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID " + 
			"AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND M.MOVIE_ID=? AND DATE(MOVIE_SHOW_TIME_DATE) = ?";

	@Override
	public List<CinemaHall> getCinemaHallInformations(String city, Long movieId, LocalDate date) {
		city="KOLK";
		Map<CinemaHall, Map<Screen, List<ShowTime>>>  cinemaHalls = new HashMap<>();
		jdbcTemplate.query(CINEMA_HALLS_BY_CITY_MOVIE, new Object[] { city, movieId, date } ,rs -> {
			CinemaHall ch = CinemaHall.builder()
						.cinemaHallId(rs.getLong("CINEMA_HALL_ID"))
						.cinemaHallName(rs.getString("CINEMA_HALL_NAME"))
						.cinemaHallShortName(rs.getString("CINEMA_HALL_SHORT_NAME"))
					.build();
			Screen screen = Screen.builder().screenId(rs.getLong("SCREEN_ID")).screenName(rs.getString("SCREEN_NAME")).build();
			ShowTime showTime = ShowTime.builder().movieShowId(rs.getLong("MOVIE_SHOW_TIME_ID")).movieShowStartTime(rs.getString("MOVIE_SHOW_TIME_STARTTIME")).build();
			
			Map<Screen, List<ShowTime>> screenMap = cinemaHalls.get(ch);
			if(screenMap == null) {
				screenMap = new HashMap<>();
			}
			
			List<ShowTime> showTimes;
			
			if(screenMap.get(screen) != null) {
				showTimes = screenMap.get(screen);
			} else {
				showTimes = new ArrayList<>();
				
			}
			showTimes.add(showTime);
			screenMap.put(screen, showTimes);
			cinemaHalls.put(ch, screenMap);
		});
		
		return cinemaHalls.entrySet().stream().map(entry -> new CinemaHall(entry.getKey(), entry.getValue())).collect(Collectors.toList());
	}

	private static final String GENERIC_SEARCH = "SELECT MOVIE_ID, MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, GENERE, COUNTRY, RELEASE_DATE, 'MOVIE' TYPE " + 
			" FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M " + 
			"WHERE  " + 
			"C.CITY_SHORT_NAME = 'KOLK' AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID " + 
			"AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID " + 
			"AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID " + 
			"AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND DATE(MOVIE_SHOW_TIME_DATE) = ? AND M.MOVIE_TITLE like ? " + 
			"UNION " + 
			"SELECT MOVIE_ID, MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, GENERE, COUNTRY, RELEASE_DATE, 'GENERE' TYPE " + 
			" FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M " + 
			"WHERE  " + 
			"C.CITY_SHORT_NAME = 'KOLK' AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID " + 
			"AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID " + 
			"AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID " + 
			"AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND DATE(MOVIE_SHOW_TIME_DATE) = ? AND M.GENERE like ? " + 
			"UNION " + 
			"SELECT MOVIE_ID, MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, GENERE, COUNTRY, RELEASE_DATE, 'LANG' TYPE " + 
			" FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M " + 
			"WHERE  " + 
			"C.CITY_SHORT_NAME = 'KOLK' AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID " + 
			"AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID " + 
			"AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID " + 
			"AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND DATE(MOVIE_SHOW_TIME_DATE) = ? AND M.LANG like ? ";
	
	public GenericSearch getMoviesBySearching(Payload payload, LocalDate date) {
		List<Movie> movie = new ArrayList<Movie>();
		List<Movie> lang = new ArrayList<Movie>();
		List<Movie> genere = new ArrayList<Movie>();
		
		jdbcTemplate.query(GENERIC_SEARCH, new Object[] { date, "%"+payload.getSearchText()+"%", date, "%"+payload.getSearchText()+"%", date, "%"+payload.getSearchText()+"%" }, (rs)-> {
			
			switch(rs.getString("TYPE")) {
			case "MOVIE":
					movie.add(getMovies(rs));
					break;
			case"GENERE":
					genere.add(getMovies(rs));
					break;
			case "LANG":
					lang.add(getMovies(rs));
					break;
			}
				
		});
		return GenericSearch.builder().genere(genere).language(lang).movie(movie).build();
	}
	
	private static final String FETCH_ALL_SEATS_IN_A_SCREEN = "SELECT SCREEN_SEAT_ID, SEAT_NUMBER, SEAT_TYPE " + 
			"FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M, SCREEN_SEAT SS " + 
			"WHERE  " + 
			"C.CITY_SHORT_NAME = ? AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID " + 
			"AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID " + 
			"AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID " + 
			"AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND M.MOVIE_ID= ? " + 
			"AND SS.SCREEN_CINEMA_HALL_SEAT_ID = S.SCREEN_ID " + 
			"AND CH.CINEMA_HALL_ID=? AND S.SCREEN_ID=? AND MST.MOVIE_SHOW_TIME_ID= ? AND DATE(MOVIE_SHOW_TIME_DATE) = ?";

	@Override
	public List<ScreenSeat> getScreenSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId, Long movieShowTimeId, LocalDate date) {
		return jdbcTemplate.query(FETCH_ALL_SEATS_IN_A_SCREEN, new Object[] { cityId, movieId, cinemaHallId, screenId, movieShowTimeId, date } , new ScreenSetMapper());
	}
	
	private class ScreenSetMapper implements RowMapper<ScreenSeat> {
		@Override
		public ScreenSeat mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return ScreenSeat.builder()
					.seatId(rs.getLong("SCREEN_SEAT_ID"))
					.seatNo(rs.getString("SEAT_NUMBER"))
					.seatType(SeatType.valueOf(rs.getString("SEAT_TYPE")))
					.build();
		}
	}

	private static final String FETCH_ALL_BOOKED_SEATS_IN_A_SCREEN = "select ss.SCREEN_SEAT_ID, SHOW_SEAT_STATUS from " + 
			"MOVIE_SHOW_TIME mst, screen s, SHOW_SEAT ss, city c, CINEMA_HALL CH  where MST.MOVIE_SHOW_TIME_ID=?  " + 
			"AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID and S.SCREEN_ID =? and MST.MOVIE_SHOW_TIME_MOVIE_ID=mst.MOVIE_SHOW_TIME_ID " + 
			"AND SHOW_SEAT_STATUS <> 2 " + 
			"and C.CITY_SHORT_NAME = ? AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID   " + 
			"AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID AND DATE(MOVIE_SHOW_TIME_DATE) = ? ";
	
	@Override
	public List<ShowSeat> getScreenBookedSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId,
			Long movieShowTimeId, LocalDate date) {
		return jdbcTemplate.query(FETCH_ALL_BOOKED_SEATS_IN_A_SCREEN, new Object[] { movieShowTimeId, screenId, cityId, date } , new ShowSeatMapper());
	}
	
	private class ShowSeatMapper implements RowMapper<ShowSeat> {
		@Override
		public ShowSeat mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return ShowSeat.builder()
					.bookedSeatId(rs.getLong("SCREEN_SEAT_ID"))
					//.status(rs.getString("SHOW_SEAT_STATUS"))
				//	.seatId(rs.getLong("SCREEN_SEAT_ID"))
					.build();
		}
	}

}
