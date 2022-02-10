package com.example.api.callbacks;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paypal.api.payments.PaymentHistory;

public class CallbackAPis {
	
	@PostMapping("/callback/sucess/{bookingId}")
	public void getSuccessCallback(@RequestBody PaymentHistory history) {
		history.getPayments().get(0).getTransactions().get(0).getReferenceId();
		//update this reference id in Payment table
	}

	@PostMapping("/callback/sucess/{bookingId}")
	public void getCancellationCallback(@RequestBody PaymentHistory history) {
		history.getPayments().get(0).getTransactions().get(0).getReferenceId();
		history.getPayments().get(0).getFailedTransactions().get(0).getMessage();
		//update this reference id in Payment table with failure reason
	}
}
