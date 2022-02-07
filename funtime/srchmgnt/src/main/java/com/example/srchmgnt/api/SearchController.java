package com.example.srchmgnt.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.handlers.ResponseHandlers;
import com.example.model.ServiceResponse;
import com.example.models.CinemaHall;
import com.example.models.City;
import com.example.models.GenericSearch;
import com.example.models.Movie;
import com.example.models.ScreenSeat;
import com.example.models.ShowSeat;
import com.example.payload.Payload;
import com.example.srchmgnt.service.ISearchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SearchController {
	
	private final ISearchService searchService;
	
	/**
	 * Search all city
	 * 
	 * @return
	 */
	@GetMapping("/city")
	public ResponseEntity<ServiceResponse<List<City>>> allCity() {
		return new ResponseHandlers<List<City>>().defaultResponse(searchService.getAllCities());
	}
	
	/**
	 * movies in a city in a given date
	 * 
	 * @param city
	 * @param date
	 * @return
	 */
	@GetMapping("/{city}/movies")
	public ResponseEntity<ServiceResponse<List<Movie>>> getMoviesInCity(
			@PathVariable("city") String city, 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@RequestParam("date") LocalDate date) {
		searchService.isDateInValidRange(date);
		return new ResponseHandlers<List<Movie>>().defaultResponse(searchService.getMoviesByCity(city, date));
	}
	
	/**
	 * movies running in halls in a city and date
	 * 
	 * @param city
	 * @param movieId
	 * @param date
	 * @return
	 */
	@GetMapping("/{city}/{movie}/cinemahalls")
	public ResponseEntity<ServiceResponse<List<CinemaHall>>> getMoviesInCity(
			@PathVariable("city") String city, 
			@PathVariable("movie") Long movieId, 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@RequestParam("date") LocalDate date) {
		return new ResponseHandlers<List<CinemaHall>>().defaultResponse(searchService.getCinemaHallInformations(city, movieId, date));
	}
	
	@PostMapping("/{city}/search")
	public ResponseEntity<ServiceResponse<GenericSearch>> getMoviesBySearching(
			@RequestBody Payload payload, 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@RequestParam("date") LocalDate date) {
		return new ResponseHandlers<GenericSearch>().defaultResponse(searchService.getMoviesBySearching(payload, date));
	}
	
	@GetMapping("/{city}/{movie}/{cinemaHall}/{screen}/{movieShow}")
	public ResponseEntity<ServiceResponse<List<ScreenSeat>>> getSeatsByScreen(
			@PathVariable("city") String cityId, 
			@PathVariable("movie") Long movieId, 
			@PathVariable("cinemaHall") Long cinemaHallId, 
			@PathVariable("screen") Long screenId,
			@PathVariable("movieShow") Long movieShowTimeId, 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@RequestParam("date") LocalDate date) {
		return new ResponseHandlers<List<ScreenSeat>>().defaultResponse(searchService.getScreenSeats(cityId, movieId, cinemaHallId, screenId, movieShowTimeId, date));
	}
	
	@GetMapping("/{city}/{movie}/{cinemaHall}/{screen}/{movieShow}/bookedseats")
	public ResponseEntity<ServiceResponse<List<ShowSeat>>> getBookedSeatsByScreen(
			@PathVariable("city") String cityId, 
			@PathVariable("movie") Long movieId, 
			@PathVariable("cinemaHall") Long cinemaHallId, 
			@PathVariable("screen") Long screenId,
			@PathVariable("movieShow") Long movieShowTimeId, 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@RequestParam("date") LocalDate date) {
		return new ResponseHandlers<List<ShowSeat>>().defaultResponse(searchService.getScreenBookedSeats(cityId, movieId, cinemaHallId, screenId, movieShowTimeId, date));
	}
}
