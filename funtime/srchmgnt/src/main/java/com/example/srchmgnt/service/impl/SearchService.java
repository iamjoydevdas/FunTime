package com.example.srchmgnt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.GenericSearch;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.models.ScreenSeat;
import com.example.models.ShowTime;
import com.example.payload.Payload;
import com.example.srchmgnt.repo.ISearchRepo;
import com.example.srchmgnt.service.ISearchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SearchService implements ISearchService {
	private final ISearchRepo searchRepo;
	
	@Override
	public List<City> getAllCities() {
		return searchRepo.getAllCities();
	}

	@Override
	public List<Movie> getMoviesByCity(String city) {
		return searchRepo.getMoviesByCity(city);
	}

	@Override
	public List<CinemaHall> getCinemaHallInformations(String city, Long movieId) {
		// TODO Auto-generated method stub
		return searchRepo.getCinemaHallInformations(city, movieId);
	}

	@Override
	public GenericSearch getMoviesBySearching(Payload payload) {
		// TODO Auto-generated method stub
		return searchRepo.getMoviesBySearching(payload);
	}

	@Override
	public List<ScreenSeat> getScreenSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId,
			Long movieShowTimeId) {
		return searchRepo.getScreenSeats(cityId, movieId, cinemaHallId, screenId, movieShowTimeId);
	}


}
