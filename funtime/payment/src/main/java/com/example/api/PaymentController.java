package com.example.api;

import org.apache.commons.collections.FunctorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.IPaymentApis;
import com.example.Payment;
import com.example.service.PaymentService;
import com.paypal.base.rest.PayPalRESTException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class PaymentController implements IPaymentApis {
	private PaymentService service;
	
	@Override
	public ResponseEntity<Payment> executePayment(Payment payment) {
		try {
			com.paypal.api.payments.Payment createPayment = service.createPayment(payment);
			service.executePayment(createPayment.getPaymentInstruction().getReferenceNumber(), payment.getUser().getEmail());
		} catch (PayPalRESTException e) {
			throw new FunctorException("Issue in payment gateway");
		}
		return null;
	}

}
