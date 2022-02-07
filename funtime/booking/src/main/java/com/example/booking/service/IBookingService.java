package com.example.booking.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.payload.Payload;

public interface IBookingService {
	String checkout(@RequestBody Payload payload);

	String confirmBooking(@RequestBody Payload payload);

	String bookingStatus(@PathVariable("bookingId") String bookingId);
}
