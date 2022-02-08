package com.example.discount.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.discount.model.DiscountEligible;

public interface IDiscountAPi {
	@PostMapping("/discount")
	public DiscountEligible getDiscount(@RequestBody DiscountEligible discountEligible);
}
