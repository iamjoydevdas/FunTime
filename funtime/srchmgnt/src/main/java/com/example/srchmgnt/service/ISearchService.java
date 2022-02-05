package com.example.srchmgnt.service;

import java.util.List;
import java.util.Map;

import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.models.ShowTime;

public interface ISearchService {

	List<City> getAllCities();
	
	List<Movie> getMoviesByCity(String city);
	
	List<CinemaHall> getCinemaHallInformations(String city, Long movieId);
}