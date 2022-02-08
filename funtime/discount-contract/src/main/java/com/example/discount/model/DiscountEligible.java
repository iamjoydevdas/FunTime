package com.example.discount.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiscountEligible {
	private boolean discountEligibleCity;
	private boolean discountEligibleCinemaHall;
	private boolean discountEligibleAfterNoonShow;
	private Long noOfTickets;
	private Double actualPrice;
	
	private Long thirdTicketDiscount;
	private Long afterNoonShowDiscount;
	private String discountDesc;
}
