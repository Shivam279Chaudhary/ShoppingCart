package com.assignment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Test
	void correctOrderDetailsWhenCorrectCustomerIdGiven() throws Exception {
		mockMvc.perform(get("/customers/1/orders"))
		.andExpect(status().isOk())
//		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[0].orderId", is(102)))
		.andExpect(jsonPath("$.[0].totalAmount", is(262)))
		.andExpect(jsonPath("$.[0].customerId", is(1)))
		.andExpect(jsonPath("$.[0].orderItems.[0].orderItemId", is(103)))
		.andExpect(jsonPath("$.[0].orderItems.[0].productId", is(1)))
		.andExpect(jsonPath("$.[0].orderItems.[0].quantity", is(10)))
		.andExpect(jsonPath("$.[0].orderItems.[0].pricePerUnit", is(10)));
	}

}
