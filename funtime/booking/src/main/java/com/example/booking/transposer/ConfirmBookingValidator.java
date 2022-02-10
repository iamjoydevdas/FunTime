package com.example.booking.transposer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.booking.dto.IBookingValidator;
import com.example.payload.Payload;

import lombok.AllArgsConstructor;

@Aspect  
@Component 
@AllArgsConstructor
public class ConfirmBookingValidator {
	private final IBookingValidator bookingValidator;

	@Before(value = "execution(* com.example.booking.dto.impl.BookingRepo.confirmBooking(..)) and args(payload)")
	public void isWaitingTimeExceeded(JoinPoint joinPoint, Payload payload) {
		if(bookingValidator.isWaitingTimeExceeded(payload)) {
			System.out.println("Exception");
		//	throw new FunTimeException(HttpStatus.NOT_FOUND, "Waiting time exceeded for this booking.");
		}
	}


	@Before(value =
			"execution(* com.example.booking.dto.impl.BookingRepo.confirmBooking(..)) and args(payload)"
			) 
	public void isThereAnyOtherBookingOnTheSeats(JoinPoint joinPoint, Payload payload) {
		bookingValidator.isSeatBookedWithOtherUsers(payload);
	}

}
