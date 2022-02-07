package com.example.booking.service.impl;

import org.springframework.stereotype.Service;

import com.example.booking.dto.IBookingRepo;
import com.example.booking.service.IBookingService;
import com.example.payload.Payload;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingService implements IBookingService {
	private final IBookingRepo bookingRepo;
	
	@Override
	public String checkout(Payload payload) {
		return bookingRepo.checkout(payload);
	}

	@Override
	public String confirmBooking(Payload payload) {
		return bookingRepo.confirmBooking(payload);
	}

	@Override
	public String bookingStatus(String bookingId) {
		return null;
	}
	
}
