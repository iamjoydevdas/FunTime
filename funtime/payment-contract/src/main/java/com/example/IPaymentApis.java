package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IPaymentApis {
	
	
	@PostMapping("/payment")
	public ResponseEntity<Payment> executePayment(@RequestBody Payment payment);
}
