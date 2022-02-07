package com.example.discount.model;

import lombok.Data;

@Data
public class DiscountEligible {
	private boolean discountEligibleCity;
	private boolean discountEligibleCinemaHall;
	private boolean discountEligibleAfterNoonShow;
	private Long noOfTickets;
	
	private Long thirdTicketDiscount;
	private Long afterNoonShowDiscount;
	private String discountDesc;
}
