package com.example;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Payment {
	private Double amountToPay;
	private String callbackApiSuccess;
	private String callbackApiFailure;
	private Long remoteTransactionId;
	private PaymentMethod paymentMethod;
	private Long bookingId;
	private User user;
}
