package com.example.srchmgnt.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.models.ShowTime;
import com.example.shared.handlers.ResponseHandlers;
import com.example.shared.model.ServiceResponse;
import com.example.srchmgnt.service.ISearchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SearchController {
	
	private final ISearchService searchService;
	
	@GetMapping("/city")
	public ResponseEntity<ServiceResponse<List<City>>> allCity() {
		return new ResponseHandlers<List<City>>().defaultResponse(searchService.getAllCities());
	}
	
	@GetMapping("/{city}/movies")
	public ResponseEntity<ServiceResponse<List<Movie>>> getMoviesInCity(@PathVariable("city") String city) {
		return new ResponseHandlers<List<Movie>>().defaultResponse(searchService.getMoviesByCity(city));
	}
	
	@GetMapping("/{city}/{movie}/cinemahalls")
	public ResponseEntity<ServiceResponse<List<CinemaHall>>> getMoviesInCity(@PathVariable("city") String city, @PathVariable("movie") Long movieId) {
		return new ResponseHandlers<List<CinemaHall>>().defaultResponse(searchService.getCinemaHallInformations(city, movieId));
	}
}
