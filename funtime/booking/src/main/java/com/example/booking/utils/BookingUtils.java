package com.example.booking.utils;

import com.example.discount.model.DiscountEligible;

public class BookingUtils {
	public static Double getFinalAmount(DiscountEligible discountEligible) {
		Double finalPrice = discountEligible.getActualPrice();
		
		//TODO calculate 3rd ticket and
		if(discountEligible.getThirdTicketDiscount() !=0) {
			//Recalculate actual price
		}
		
		if(discountEligible.getAfterNoonShowDiscount() != 0) {
			finalPrice = (discountEligible.getActualPrice() * discountEligible.getAfterNoonShowDiscount())/100; 
		}
		
		return finalPrice;
		
	}
}
