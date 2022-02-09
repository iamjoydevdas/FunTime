package com.example.booking.api;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.IBookingApi;
import com.example.booking.service.IBookingService;
import com.example.handlers.ResponseHandlers;
import com.example.model.ServiceResponse;
import com.example.models.Booking;
import com.example.payload.Payload;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class BookingAPis implements IBookingApi  {
	private final IBookingService bookingService;

	@Override
	public ResponseEntity<ServiceResponse<String>> checkout(Payload payload) {
		return new ResponseHandlers<String>().defaultResponse(bookingService.checkout(payload));
	}

	@Override
	public ResponseEntity<ServiceResponse<String>> confirmBooking(Payload payload) {
		bookingService.confirmBooking(payload);
		return new ResponseHandlers<String>().defaultResponse("Booking soft confirm.");
	}

	@Override
	public ResponseEntity<ServiceResponse<Booking>> bookingStatus(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ServiceResponse<Booking>> cancelBooking(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ServiceResponse<List<Booking>>> userBookings(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
