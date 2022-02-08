package com.example.booking.dto;

import com.example.models.Booking;
import com.example.payload.Payload;

public interface IBookingRepo {
	String checkout(Payload payload);
	
	void confirmBooking(Payload payload);
	
	Booking bookingStatus(String bookingId);
}
