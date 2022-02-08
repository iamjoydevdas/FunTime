package com.example.booking.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Booking;
import com.example.payload.Payload;

public interface IBookingService {
	String checkout(@RequestBody Payload payload);

	void confirmBooking(@RequestBody Payload payload);

	Booking bookingStatus(@PathVariable("bookingId") String bookingId);
}
