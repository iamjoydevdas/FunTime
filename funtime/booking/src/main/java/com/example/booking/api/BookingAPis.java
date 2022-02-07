package com.example.booking.api;


import org.springframework.web.bind.annotation.RestController;

import com.example.IBookingApi;
import com.example.payload.Payload;

@RestController
public class BookingAPis implements IBookingApi  {
	
	public String ok() {
		return "hello";
	}

	@Override
	public String checkout(Payload payload) {
		
		return null;
	}

}
