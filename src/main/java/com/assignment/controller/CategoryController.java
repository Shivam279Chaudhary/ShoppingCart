package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Category;
import com.assignment.model.Product;
import com.assignment.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@GetMapping("/categories/{id}/products")
	public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable int id) {
		List<Product> products = categoryService.getAllProductsByCategoryId(id);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping("/categories")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		int id = categoryService.addCategory(category);
		return new ResponseEntity<String>("Category with id : " + id + " added", HttpStatus.CREATED);
	}
}
