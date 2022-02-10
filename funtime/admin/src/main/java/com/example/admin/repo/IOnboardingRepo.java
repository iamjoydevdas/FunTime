package com.example.admin.repo;

import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.Movie;


public interface IOnboardingRepo {
	Long addNewCity(City c);
	Long addNewCinemaHall(CinemaHall ch);
	Long addMovie(Movie m);
}
