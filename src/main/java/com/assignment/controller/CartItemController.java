package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.CartItem;
import com.assignment.model.CartItemDTO;
import com.assignment.service.CartItemService;

@RestController
public class CartItemController {

	@Autowired
	CartItemService cartItemService;

	@PostMapping("/carts/{cartId}/cartitems/{cartItemId}")
	public ResponseEntity<CartItem> updateParticularCartItem(@RequestBody CartItemDTO cartItemDTO,
			@PathVariable int cartId, @PathVariable int cartItemId) {
		CartItem cartItem = cartItemService.updateCart(cartId, cartItemId, cartItemDTO);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
}
