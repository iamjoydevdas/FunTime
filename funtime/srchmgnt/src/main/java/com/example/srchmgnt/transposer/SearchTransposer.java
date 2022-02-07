package com.example.srchmgnt.transposer;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SearchTransposer {
	
	/*
	 * @Before(value =
	 * "execution(* com.example.srchmgn.SearchManagement.*(..)) and args(payload)")
	 * public void isValidSearchDate(JoinPoint joinPoint, Payload payload) {
	 * if(bookingValidator.isWaitingTimeExceeded(payload)) {
	 * System.out.println("Exception"); // throw new
	 * FunTimeException(HttpStatus.NOT_FOUND,
	 * "Waiting time exceeded for this booking."); } }
	 */
}
