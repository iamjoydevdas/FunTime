package com.example.admin.service.impl;

import org.springframework.stereotype.Service;

import com.example.admin.repo.IOnboardingRepo;
import com.example.admin.service.IOnboardingService;
import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.Movie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OnboardingService implements IOnboardingService{

	public final IOnboardingRepo repo;
	
	@Override
	public Long addNewCity(City c) {
		// TODO Auto-generated method stub
		return repo.addNewCity(c);
	}

	@Override
	public Long addNewCinemaHall(CinemaHall ch) {
		// TODO Auto-generated method stub
		return repo.addNewCinemaHall(ch);
	}

	@Override
	public Long addMovie(Movie m) {
		// TODO Auto-generated method stub
		return repo.addMovie(m);
	}

}
