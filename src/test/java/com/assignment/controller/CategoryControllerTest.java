package com.assignment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
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
import com.assignment.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Test
	void showAllCategoriesInHappyScenario() throws Exception {
		mockMvc.perform(get("/categories"))
				.andExpect(status().isOk())
//				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$.[0].categoryId", is(16)))
				.andExpect(jsonPath("$.[0].name", is("Electronics")));
	}
	
	// works fine
//	@Test
	void addingNewCategory() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Category category = new Category();
		category.setCategoryId(17);
		category.setName("Clothing");
		String categoryToAdd = objectMapper.writeValueAsString(category);
		mockMvc.perform(post("/categories")
						.content(categoryToAdd)
						.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)
						)
					.andExpect(status().isCreated());

	}
	
	@Test
	void correctProductsWhenCorrectCategoryIdGiven() throws Exception {
		mockMvc.perform(get("/categories/89/products"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].productId", is(2)))
		.andExpect(jsonPath("$.[0].name", is("shirt")))
		.andExpect(jsonPath("$.[0].pricePerUnit", is(1000)))
		.andExpect(jsonPath("$.[0].category.categoryId", is(89)))
		.andExpect(jsonPath("$.[0].category.name", is("Clothing")));
	}
	
	@Test
	void categoryNotFoundExceptionWhenIncorrectCategoryIdGiven() throws Exception {
		mockMvc.perform(get("/categories/10/products"))
		.andExpect(status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof CategoryNotFoundException));
	}


}
