package com.example.booking.dto;

import com.example.payload.Payload;

public interface IBookingValidator {
	boolean isWaitingTimeExceeded(Payload payload);

	boolean isSeatBookedWithOtherUsers(Payload payload);
}
