package com.example.discount.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.discount.model.DiscountEligible;
import com.example.discount.service.IDiscountService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class DiscountApi {
	private final IDiscountService discountService;
	
	@GetMapping("/discount")
	public DiscountEligible getDiscount(@RequestBody DiscountEligible discountEligible) {
		return discountService.calculateDiscount(discountEligible);
	}
}
