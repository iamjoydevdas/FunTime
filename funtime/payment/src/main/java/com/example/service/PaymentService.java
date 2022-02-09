package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaymentService {
	@Autowired
	private APIContext apiContext;
	
	public Payment createPayment(
			com.example.Payment payment ) throws PayPalRESTException{
		Amount amount = new Amount();
		amount.setCurrency(String.valueOf(payment.getAmountToPay()));

		Transaction transaction = new Transaction();
		transaction.setDescription("Transaction for booking id "+payment.getBookingId());
		transaction.setAmount(amount);

		List transactions = new ArrayList<>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPayerInfo(new PayerInfo().setEmail(payment.getUser().getEmail()));
		payer.setPaymentMethod(payment.getPaymentMethod().toString());

		Payment seek = new Payment();
		seek.setIntent(payment.getRemoteTransactionId().toString());
		seek.setPayer(payer);  
		seek.setTransactions(transactions);
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(payment.getCallbackApiFailure());
		redirectUrls.setReturnUrl(payment.getCallbackApiSuccess());
		seek.setRedirectUrls(redirectUrls);

		return seek.create(apiContext);
	}
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		return payment.execute(apiContext, paymentExecute);
	}
}
