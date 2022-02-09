package com.example.booking.screening.impl;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.DiscountFeign;
import com.example.booking.dto.IDiscountRepo;
import com.example.discount.model.DiscountEligible;
import com.example.payload.Payload;

import lombok.AllArgsConstructor;

@Aspect
@Component
@AllArgsConstructor
public class DiscountScreening {
	private final IDiscountRepo discountRepo;
	private final DiscountFeign feign;
	
	@After(value = "execution(* com.example.booking.dto.impl.BookingRepo.confirmBooking(..)) and args(payload)")
	public void calculateDiscount(Payload payload) {
		DiscountEligible eligibleDiscount = discountRepo.getEligibleDiscount(payload.getBookingId());
		DiscountEligible discount = feign.getDiscount(eligibleDiscount);
		discountRepo.updatePrice(payload.getBookingId(), discount);
	}
}
