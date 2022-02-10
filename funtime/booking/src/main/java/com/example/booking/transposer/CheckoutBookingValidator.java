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
public class CheckoutBookingValidator {
	private final IBookingValidator bookingValidator;

	@Before(value = "execution(* com.example.booking.dto.impl.BookingRepo.checkout(..)) and args(payload)")
	public void isSeatTakenByOthers(JoinPoint joinPoint, Payload payload) {
		// TODO this is query and check if that seat is reserved for others and provide necessary exception msg if present
	}


	@Before(value =
			"execution(* com.example.booking.dto.impl.BookingRepo.confirmBooking(..)) and args(payload)"
			) 
	public void isTicketSoldOutForThisScreen(JoinPoint joinPoint, Payload payload) {
		// TODO this is query will check if that hall is full and provide necessary exception msg if present
	}
}
