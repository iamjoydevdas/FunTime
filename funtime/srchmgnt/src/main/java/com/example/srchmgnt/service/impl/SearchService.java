package com.example.srchmgnt.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.exception.FunTimeException;
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
import com.example.srchmgnt.service.ISearchService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchService implements ISearchService {
	private final ISearchRepo searchRepo;
	
	@Override
	public List<City> getAllCities() {
		return searchRepo.getAllCities();
	}

	@Override
	public List<Movie> getMoviesByCity(String city, LocalDate date) {
		return searchRepo.getMoviesByCity(city, date);
	}

	@Override
	public List<CinemaHall> getCinemaHallInformations(String city, Long movieId, LocalDate date) {
		// TODO Auto-generated method stub
		return searchRepo.getCinemaHallInformations(city, movieId, date);
	}

	@Override
	public GenericSearch getMoviesBySearching(Payload payload, LocalDate date) {
		// TODO Auto-generated method stub
		return searchRepo.getMoviesBySearching(payload, date);
	}

	@Override
	public List<ScreenSeat> getScreenSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId,
			Long movieShowTimeId, LocalDate date) {
		return searchRepo.getScreenSeats(cityId, movieId, cinemaHallId, screenId, movieShowTimeId, date);
	}

	@Override
	public List<ShowSeat> getScreenBookedSeats(String cityId, Long movieId, Long cinemaHallId, Long screenId,
			Long movieShowTimeId, LocalDate date) {
		// TODO Auto-generated method stub
		return searchRepo.getScreenBookedSeats(cityId, movieId, cinemaHallId, screenId, movieShowTimeId, date);
	}

	@Value("${booking.prebooking.advance.allowed-days}")
	private Integer allowedDays;
	
	@Override
	public void isDateInValidRange(LocalDate date) {
		if(date.isBefore(LocalDate.now().plusDays(10)) && date.isAfter(LocalDate.now().minusDays(1))) {
			log.info("date is in range");
		}else {
			throw new FunTimeException("Date Range is not correct");
		}
	}


}
