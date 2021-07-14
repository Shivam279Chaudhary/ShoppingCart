package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Product;
import com.assignment.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return new ResponseEntity<String>("Product added !!!", HttpStatus.CREATED);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		productService.deleteProductById(id);
		return new ResponseEntity<String>("Product with id :" + id + " has been deleted", HttpStatus.OK);
	}
}
