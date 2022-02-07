package com.example.srchmgnt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.GenericSearch;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.models.ScreenSeat;
import com.example.models.ShowSeat;
import com.example.models.ShowTime;
import com.example.payload.Payload;

public interface ISearchService {
	void isDateInValidRange(LocalDate date);

	List<City> getAllCities();
	
	List<Movie> getMoviesByCity(String city, LocalDate date);
	
	List<CinemaHall> getCinemaHallInformations(String city, Long movieId, LocalDate date);
	
	GenericSearch getMoviesBySearching(Payload payload, LocalDate date);
	
	List<ScreenSeat> getScreenSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId, Long movieShowTimeId, LocalDate date);
	
	List<ShowSeat> getScreenBookedSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId, Long movieShowTimeId, LocalDate date);
}
