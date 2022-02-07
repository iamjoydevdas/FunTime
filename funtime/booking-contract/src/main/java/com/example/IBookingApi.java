package com.example;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.payload.Payload;

public interface IBookingApi {
	
	@PostMapping(value="/booking")
	public String checkout(@RequestBody Payload payload);
	
	@PutMapping(value="/booking/confirm")
	public String confirmBooking(@RequestBody Payload payload);
	
	@GetMapping(value="/{bookingId}/status")
	public String bookingStatus(@PathVariable("bookingId") String bookingId);
}
