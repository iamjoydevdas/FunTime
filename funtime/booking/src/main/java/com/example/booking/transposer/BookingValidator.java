package com.example.booking.transposer;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.booking.dto.IBookingValidator;
import com.example.exception.FunTimeException;
import com.example.payload.Payload;

import lombok.AllArgsConstructor;

@Aspect  
@Component 
@AllArgsConstructor
public class BookingValidator {
	private final IBookingValidator bookingValidator;
	
	@Before(value = "execution(* com.example.booking.dto.impl.BookingRepo.confirmBooking(..)) and args(payload)")
	public void validate(JoinPoint joinPoint, Payload payload) {
		if(!bookingValidator.isWaitingTimeExceeded(payload)) {
			throw new FunTimeException(HttpStatus.NOT_FOUND, "Waiting time exceeded for this booking.");
		}
	}
	
	/*
	 * @Before(value =
	 * "execution(* com.example.booking.dto.impl.BookingRepo.confirmBooking(..)) and args(payload)"
	 * ) public void validate1(JoinPoint joinPoint, Payload payload) {
	 * System.out.println("I am executing 2"); }
	 */
}
