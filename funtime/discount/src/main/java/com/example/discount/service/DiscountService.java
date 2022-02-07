package com.example.discount.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.discount.model.DiscountEligible;

@Service
public class DiscountService implements IDiscountService {
	@Autowired
	KieContainer kieContainer;

	@Override
	public DiscountEligible calculateDiscount(DiscountEligible discountEligible) {
		KieSession session = kieContainer.newKieSession();
		session.setGlobal("discountEligible", discountEligible);
		session.insert(discountEligible);
		session.fireAllRules();
		session.dispose();
		/*
		 * if(StringUtils.isEmpty(discount.getDiscountName())) { throw new
		 * BusReservationException("Invalid Coupon code"); }
		 */
		return discountEligible;
	}

}
