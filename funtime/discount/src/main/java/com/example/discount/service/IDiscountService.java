package com.example.discount.service;

import com.example.discount.model.DiscountEligible;

public interface IDiscountService {
	DiscountEligible calculateDiscount(DiscountEligible discountEligible);
}
