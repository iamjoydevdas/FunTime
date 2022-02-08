package com.example.booking.dto;

import com.example.discount.model.*;

public interface IDiscountRepo {
	DiscountEligible getEligibleDiscount(Long bookingId);
	
	void updatePrice(Long bookingId, DiscountEligible discountEligible);
}
