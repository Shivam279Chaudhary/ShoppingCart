package com.assignment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.assignment.exception.CategoryNotFoundException;
import com.assignment.exception.ProductNotFoundException;
import com.assignment.model.Category;
import com.assignment.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Test
	void correctProductWhenCorrectProductIdGiven() throws Exception {
		mockMvc.perform(get("/products/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.productId", is(1)))
		.andExpect(jsonPath("$.name", is("laptop")))
		.andExpect(jsonPath("$.pricePerUnit", is(1000)))
		.andExpect(jsonPath("$.category.categoryId", is(16)))
		.andExpect(jsonPath("$.category.name", is("Electronics")));
	}
	
	@Test
	void productNotFoundExceptionWhenIncorrectProductIdGiven() throws Exception {
		mockMvc.perform(get("/products/10"))
		.andExpect(status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException));
	}
	
	// works fine
//	@Test
	void testingProductInsertionWhenCorrectInputsGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = new Product();
		product.setCategory(new Category(16,"Electronics", null));
		product.setProductId(100);
		product.setName("Clock");
		product.setPricePerUnit(100);
		String productToAdd = objectMapper.writeValueAsString(product);
		mockMvc.perform(post("/products")
						.content(productToAdd)
						.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)
						)
					.andExpect(status().isCreated());

	}
	
	@Test
	void noProductInsertionWhenIncorrectInputsGiven() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = new Product();
		product.setCategory(new Category(17,"Electronics", null));
		product.setProductId(100);
		product.setName("Clock");
		product.setPricePerUnit(100);
		String productToAdd = objectMapper.writeValueAsString(product);
		mockMvc.perform(post("/products")
						.content(productToAdd)
						.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)
						)
						.andExpect(result -> assertTrue(result.getResolvedException() instanceof CategoryNotFoundException))
						.andExpect(status().isNotFound());

	}

}
