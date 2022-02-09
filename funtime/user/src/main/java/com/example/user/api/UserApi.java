package com.example.user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.BookingFeign;
import com.example.IUserApi;
import com.example.handlers.ResponseHandlers;
import com.example.model.ServiceResponse;
import com.example.models.Booking;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserApi implements IUserApi{
	
	private final BookingFeign bookingFeign;
	
	@Override
	public ResponseEntity<ServiceResponse<String>> cancelBooking(String userId, String bookingId) {
		bookingFeign.cancelBooking(bookingId);
		return new ResponseHandlers().defaultResponse("Booking cancelled");
	}

	@Override
	public ResponseEntity<ServiceResponse<List<Booking>>> getMyBookings(String userId) {
		return bookingFeign.userBookings(userId);
	}

	@Override
	public ResponseEntity<ServiceResponse<String>> payBooking(String userId, String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
