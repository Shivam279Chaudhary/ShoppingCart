package com.assignment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.model.CreditCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutControllerTest {
	@Autowired
	MockMvc mockMvc;

	// works order ID : 132
//	@Test
	void checkoutSuccessfulWhenCorrectInputsGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber("1122334455667788");
		creditCard.setCvv("123");
		String creditCardOfCustomer = objectMapper.writeValueAsString(creditCard);
		
		mockMvc.perform(post("/carts/3/checkout")
				.content(creditCardOfCustomer)
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.status", is("Order Placed")))
				.andExpect(jsonPath("$.message", is("Your Order has been placed !!!")))
				.andExpect(jsonPath("$.orderId", isA(Number.class)));
	}
	
	@Test
	void checkoutFailedDueToEmptyCart() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber("1122334455667788");
		creditCard.setCvv("123");
		String creditCardOfCustomer = objectMapper.writeValueAsString(creditCard);
		
		mockMvc.perform(post("/carts/4/checkout")
				.content(creditCardOfCustomer)
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status", is("Order Validation Failed")))
				.andExpect(jsonPath("$.message", is("Your cart is empty !!!")))
				.andExpect(jsonPath("$.orderId", is(-1)));
	}
	
	@Test
	void checkoutFailedDueToPaymentFailure() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber("898");
		creditCard.setCvv("123");
		String creditCardOfCustomer = objectMapper.writeValueAsString(creditCard);
		
		mockMvc.perform(post("/carts/2/checkout")
				.content(creditCardOfCustomer)
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status", is("Payment Failed")))
				.andExpect(jsonPath("$.message", is("Invalid Credit Card Details. Please check again !!")))
				.andExpect(jsonPath("$.orderId", is(-1)));
	}

}
