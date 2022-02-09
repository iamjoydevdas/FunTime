package com.example;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.ServiceResponse;
import com.example.models.Booking;
import com.example.payload.Payload;

public interface IBookingApi {
	
	@PostMapping(value="/booking")
	ResponseEntity<ServiceResponse<String>> checkout(@RequestBody Payload payload);
	
	@PutMapping(value="/booking/confirm")
	ResponseEntity<ServiceResponse<String>> confirmBooking(@RequestBody Payload payload);
	
	@GetMapping(value="/{bookingId}/status")
	ResponseEntity<ServiceResponse<Booking>> bookingStatus(@PathVariable("bookingId") String bookingId);
	
	@GetMapping(value="/{bookingId}/cancel")
	ResponseEntity<ServiceResponse<Booking>> cancelBooking(@PathVariable("bookingId") String bookingId);
	
	@GetMapping(value="/bookings/{userId}")
	ResponseEntity<ServiceResponse<List<Booking>>> userBookings(@PathVariable("userId") String userId);
}
