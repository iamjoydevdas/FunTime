package com.example.booking.dto;

import com.example.payload.Payload;

public interface IBookingRepo {
	String checkout(Payload payload);
	
	String confirmBooking(Payload payload);
}
