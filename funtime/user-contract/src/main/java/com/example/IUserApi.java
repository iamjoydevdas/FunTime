package com.example;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.model.ServiceResponse;
import com.example.models.Booking;

public interface IUserApi {
	
	@DeleteMapping("{userId}/booking/{bookingId}") 
	ResponseEntity<ServiceResponse<String>> cancelBooking(@PathVariable("userId") String userId, @PathVariable("bookingId") String bookingId);
	
	@GetMapping("{userId}/booking")
	ResponseEntity<ServiceResponse<List<Booking>>> getMyBookings(@PathVariable("userId") String userId);
	
	@PutMapping("{userId}/booking/{bookingId}/pay")
	ResponseEntity<ServiceResponse<String>> payBooking(@PathVariable("userId") String userId, @PathVariable("bookingId") String bookingId);
	
}
