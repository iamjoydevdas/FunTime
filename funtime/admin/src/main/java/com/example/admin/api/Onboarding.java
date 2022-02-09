package com.example.admin.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.repo.OnboardingRepo;
import com.example.handlers.ResponseHandlers;
import com.example.model.ServiceResponse;
import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.Movie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class Onboarding {
	private final OnboardingRepo onboarding;
	
	@PostMapping("/city")
	public ResponseEntity<ServiceResponse<Long>> onboardNewCity(@RequestBody City city) {
		return new ResponseHandlers<Long>().defaultResponse(onboarding.addNewCity(city));
	}
	
	@PostMapping("/cinema-hall")
	public ResponseEntity<ServiceResponse<Long>> onboardNewCity(@RequestBody CinemaHall cinemaHall) {
		return new ResponseHandlers<Long>().defaultResponse(onboarding.addNewCinemaHall(cinemaHall));
	}
	
	@PostMapping("/movie")
	public ResponseEntity<ServiceResponse<Long>> onboardNewCity(@RequestBody Movie movie) {
		return new ResponseHandlers<Long>().defaultResponse(onboarding.addMovie(movie));
	}
}
