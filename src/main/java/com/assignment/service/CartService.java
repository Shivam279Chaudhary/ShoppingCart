package com.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exception.CartItemNotFound;
import com.assignment.exception.CartNotFoundException;
import com.assignment.model.Cart;
import com.assignment.model.CartItem;
import com.assignment.model.CartItemDTO;
import com.assignment.repository.ICartItemRepository;
import com.assignment.repository.ICartRepository;

@Service
public class CartService {

	@Autowired
	ICartRepository cartRepository;

	@Autowired
	ICartItemRepository cartItemRepository;

	public List<Cart> getAllCarts() {
		// TODO Auto-generated method stub
		return (List<Cart>) cartRepository.findAll();
	}

	public Cart getCartById(int cartId) {
		// TODO Auto-generated method stub
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isEmpty())
			throw new CartNotFoundException("There is no cart with Id : " + cartId);

		return cart.get();
	}

	public Cart addItemToCart(int cartId, CartItem cartItem) {
		// TODO Auto-generated method stub
//		Optional<Cart> cart = cartRepository.findById(cartId);
//		if (cart.isEmpty())
//			throw new CartNotFoundException("There is no cart with Id : " + cartId);
		Cart cart = getCartById(cartId);
		cartItem.setCart(cart);
		cartItemRepository.save(cartItem);
		return cart;
	}
}
