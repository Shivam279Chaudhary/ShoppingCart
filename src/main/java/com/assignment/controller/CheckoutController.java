package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.CheckoutResponse;
import com.assignment.model.CreditCard;
import com.assignment.service.CheckoutService;

@RestController
public class CheckoutController {
	@Autowired
	private CheckoutService checkoutService;

	@PostMapping("/carts/{cartId}/checkout")
	public ResponseEntity<CheckoutResponse> checkout(@PathVariable int cartId, @RequestBody CreditCard creditCard){
		CheckoutResponse response = checkoutService.checkoutOrder(cartId,creditCard)	;
		return new ResponseEntity<CheckoutResponse>(response,HttpStatus.CREATED);
	}
}
