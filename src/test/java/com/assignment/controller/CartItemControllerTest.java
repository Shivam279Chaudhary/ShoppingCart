package com.assignment.controller;

import static org.hamcrest.CoreMatchers.is;
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

import com.assignment.exception.CartItemNotFound;
import com.assignment.exception.CartNotFoundException;
import com.assignment.model.CartItem;
import com.assignment.model.CartItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CartItemControllerTest {
	@Autowired
	MockMvc mockMvc;

//	@Test
	void cartItemUpdatedWhenCorrectInputsGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CartItemDTO cartItemDTO = new CartItemDTO();
		cartItemDTO.setCartItemId(131);
		cartItemDTO.setQuantity(11);
		String itemToAdd = objectMapper.writeValueAsString(cartItemDTO);
		mockMvc.perform(post("/carts/2/cartitems/131")
			   .content(itemToAdd).header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.cartItemId", is(131)))
			   .andExpect(jsonPath("$.productId", is(200)))
			   .andExpect(jsonPath("$.quantity", is(11)))
			   .andExpect(jsonPath("$.pricePerUnit", is(10)));
	}
	
	@Test
	void cartItemNotFoundExceptionWhenIncorrectCartItemIdGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CartItemDTO cartItemDTO = new CartItemDTO();
		cartItemDTO.setCartItemId(92);
		cartItemDTO.setQuantity(14);
		String itemToAdd = objectMapper.writeValueAsString(cartItemDTO);
		mockMvc.perform(post("/carts/1/cartitems/192")
			   .content(itemToAdd).header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
			   .andExpect(result -> assertTrue(result.getResolvedException() instanceof CartItemNotFound))
			   .andExpect(status().isNotFound());
	}
	
	@Test
	void cartNotFoundExceptionWhenIncorrectCartIdGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CartItemDTO cartItemDTO = new CartItemDTO();
		cartItemDTO.setCartItemId(92);
		cartItemDTO.setQuantity(14);
		String itemToAdd = objectMapper.writeValueAsString(cartItemDTO);
		mockMvc.perform(post("/carts/100/cartitems/92")
			   .content(itemToAdd).header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
			   .andExpect(result -> assertTrue(result.getResolvedException() instanceof CartNotFoundException))
			   .andExpect(status().isNotFound());
	}

}
