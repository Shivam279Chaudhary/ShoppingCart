package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.model.Cart;
import com.assignment.model.CheckoutResponse;
import com.assignment.model.CreditCard;
import com.assignment.model.Order;
import com.assignment.validator.OrderValidator;

@Service
public class CheckoutService {
	@Autowired
	CartService cartService;

	@Autowired
	private OrderValidator orderValidator;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OrderService orderService;

	public CheckoutResponse checkoutOrder(int cartId, CreditCard creditCard) {
		// TODO Auto-generated method stub
		Cart cart = cartService.getCartById(cartId);
		CheckoutResponse response = new CheckoutResponse();
		Boolean orderValidation = orderValidator.validate(cart);
		if (orderValidation) {
			Boolean paymentStatus = paymentService.makePayment(creditCard);
			if (paymentStatus) {
				Order order = orderService.createOrder(cart);
				response.setMessage("Your Order has been placed !!!");
				response.setOrderId(order.getOrderId());
				response.setStatus("Order Placed");
			} else {
				response.setMessage("Invalid Credit Card Details. Please check again !!");
				response.setOrderId(-1);
				response.setStatus("Payment Failed");
			}

		} else {
			response.setMessage("Your cart is empty !!!");
			response.setOrderId(-1);
			response.setStatus("Order Validation Failed");
		}

		return response;
	}

}
