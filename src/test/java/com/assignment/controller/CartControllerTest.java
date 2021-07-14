package com.assignment.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.checkerframework.checker.index.qual.EnsuresLTLengthOf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.exception.CartNotFoundException;
import com.assignment.model.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class CartControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void showAllCartsInHappyScenario() throws Exception {
		mockMvc.perform(get("/carts"))
				.andExpect(status().isOk())
//				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$.[0].cartId", is(1)))
				.andExpect(jsonPath("$.[0].customerId", is(1)))
//				.andExpect(jsonPath("$.[0].cartItems", hasSize(1)))
				.andExpect(jsonPath("$.[0].cartItems.[0].cartItemId", is(124)))
				.andExpect(jsonPath("$.[0].cartItems.[0].productId", is(1)))
				.andExpect(jsonPath("$.[0].cartItems.[0].quantity", is(3)))
				.andExpect(jsonPath("$.[0].cartItems.[0].pricePerUnit", is(100 )));
	}

	@Test
	void correctCartWhenCorrectCartIdGiven() throws Exception {
		mockMvc.perform(get("/carts/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cartId", is(1)))
				.andExpect(jsonPath("$.customerId", is(1)))
				.andExpect(jsonPath("$.cartItems.[0].cartItemId", is(124)))
				.andExpect(jsonPath("$.cartItems.[0].productId", is(1)))
				.andExpect(jsonPath("$.cartItems.[0].quantity", is(3)))
				.andExpect(jsonPath("$.cartItems.[0].pricePerUnit", is(100)));
	}

	// works fine
//	@Test
	void testingCartItemInsertionWhenCorrectInputsGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CartItem cartItem = new CartItem();
		cartItem.setProductId(200);
		cartItem.setQuantity(10);
		cartItem.setCartItemId(1);
		cartItem.setPricePerUnit(10);
		String itemToAdd = objectMapper.writeValueAsString(cartItem);
		mockMvc.perform(post("/carts/2")
						.content(itemToAdd)
						.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)
						)
					.andExpect(status().isCreated());

	}

	@Test
	void testingCartItemInsertionWhenIncorrectInputsGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CartItem cartItem = new CartItem();
		cartItem.setProductId(1);
		cartItem.setQuantity(10);
		cartItem.setCartItemId(1);
		cartItem.setPricePerUnit(10);
		String itemToAdd = objectMapper.writeValueAsString(cartItem);
		mockMvc.perform(post("/carts/100")
				.content(itemToAdd)
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof CartNotFoundException))
				.andExpect(status().isNotFound());
	}

}
