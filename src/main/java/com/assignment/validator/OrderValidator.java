package com.assignment.validator;

import org.springframework.stereotype.Component;

import com.assignment.model.Cart;

@Component
public class OrderValidator {

	public Boolean validate(Cart cart) {
		// TODO Auto-generated method stub
		if (cart == null || cart.getCartItems().size() == 0)
			return false;
		return true;
	}

}
