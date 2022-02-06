package com.example.srchmgnt.service;

import java.util.List;
import java.util.Map;

import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.GenericSearch;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.models.ScreenSeat;
import com.example.models.ShowTime;
import com.example.payload.Payload;

public interface ISearchService {

	List<City> getAllCities();
	
	List<Movie> getMoviesByCity(String city);
	
	List<CinemaHall> getCinemaHallInformations(String city, Long movieId);
	
	GenericSearch getMoviesBySearching(Payload payload);
	
	List<ScreenSeat> getScreenSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId, Long movieShowTimeId);
}
