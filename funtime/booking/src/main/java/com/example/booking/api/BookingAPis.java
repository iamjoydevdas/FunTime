package com.example.booking.api;


import org.springframework.web.bind.annotation.RestController;

import com.example.IBookingApi;
import com.example.payload.Payload;

@RestController
public class BookingAPis implements IBookingApi  {
	

	@Override
	public String checkout(Payload payload) {
		
		return null;
	}

	@Override
	public String confirmBooking(Payload payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bookingStatus(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
