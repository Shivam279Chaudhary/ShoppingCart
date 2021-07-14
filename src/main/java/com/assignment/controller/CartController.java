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

import com.assignment.model.Cart;
import com.assignment.model.CartItem;
import com.assignment.model.CartItemDTO;
import com.assignment.model.Category;
import com.assignment.service.CartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CartController {
	@Autowired
	CartService cartService;

	@GetMapping("/carts")
	public ResponseEntity<List<Cart>> getAllCarts() {
		List<Cart> carts = cartService.getAllCarts();
		return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
	}

	@PostMapping("/carts/{cartId}")
	public ResponseEntity<Cart> addItemToCart(@PathVariable int cartId, @RequestBody CartItem cartItem) {
//		log.info("received cartItem {} : cartID : {}", cartItem, cartId);
		Cart cart = cartService.addItemToCart(cartId, cartItem);
		return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
	}

	@GetMapping("/carts/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
		Cart cart = cartService.getCartById(cartId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

}
