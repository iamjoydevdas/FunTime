package com.example.booking.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingAPis {
	
	@GetMapping("/Ok")
	public String ok() {
		return "hello";
	}
}
