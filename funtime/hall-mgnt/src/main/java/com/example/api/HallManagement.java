package com.example.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hall.repo.HallRepo;
import com.example.handlers.ResponseHandlers;
import com.example.models.Screen;
import com.example.models.ScreenSeat;
import com.example.models.ShowTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class HallManagement {
	private final HallRepo hallRepo;
	
	@PostMapping("{cinemaHallId}/screen")
	public ResponseEntity<Long> addScreen(@PathVariable("cinemaHallId") Long cinemaHallId, @RequestBody Screen screen) {
		return new ResponseHandlers<Long>().defaultResponse(hallRepo.addScreenToCinemaHall(cinemaHallId, screen));
	}
	
	@PostMapping("/{screenId}/seat")
	public ResponseEntity<String> addScreen(@PathVariable("screenId") Long screenId, @RequestBody List<ScreenSeat> seats) {
		hallRepo.addSeatToScreenToCinemaHall(screenId, seats);
		return new ResponseHandlers<String>().defaultResponse("Seats updated");
	}
	
	@PostMapping("/{screenId}/{movieId}/show")
	public ResponseEntity<Long> addScreenSeats(@PathVariable("screenId") Long screenId, @PathVariable("movieId") Long movieId, @RequestBody ShowTime showTime) {
		return new ResponseHandlers<Long>().defaultResponse(hallRepo.addMovieShowTimeToScreenToCinemaHall(screenId, movieId, showTime));
	}
}
