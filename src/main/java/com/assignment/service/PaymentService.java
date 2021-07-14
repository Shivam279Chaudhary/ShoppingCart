package com.assignment.service;

import org.springframework.stereotype.Component;

import com.assignment.model.CreditCard;

@Component
public class PaymentService {

	public Boolean makePayment(CreditCard creditCard) {
		// TODO Auto-generated method stub
		if (creditCard == null)
			return false;
		// some validation logic
		if (creditCard.getCreditCardNumber() == null 
				|| creditCard.getCvv() == null
				|| creditCard.getCreditCardNumber() == "" 
				|| creditCard.getCreditCardNumber().length() != 16 
				|| creditCard.getCvv() == ""
				|| creditCard.getCvv().length() != 3
			)
			return false;
		return true;
	}

}
