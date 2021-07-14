package com.assignment.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exception.CartItemNotFound;
import com.assignment.model.Cart;
import com.assignment.model.CartItem;
import com.assignment.model.CartItemDTO;
import com.assignment.repository.ICartItemRepository;
import com.assignment.repository.ICartRepository;

@Service
public class CartItemService {
	@Autowired
	ICartRepository cartRepository;

	@Autowired
	CartService cartService;

	@Autowired
	ICartItemRepository cartItemRepository;

	public CartItem updateCart(int cartId, int cartItemId, CartItemDTO cartItemDTO) {
		// TODO Auto-generated method stub
		Cart cart = cartService.getCartById(cartId);

		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

		if (cartItem.isEmpty())
			throw new CartItemNotFound("CartItem with id : " + cartItemId + " doesn't exist");

		if (cartItemDTO.getQuantity() == 0) {
			cartItemRepository.delete(cartItem.get());
			return null;
		}

		cartItem.get().setQuantity(cartItemDTO.getQuantity());
		cartItemRepository.save(cartItem.get());
		return cartItem.get();
	}

	@Transactional
	public void deleteByCartItemId(int cartItemId) {
		// TODO Auto-generated method stub
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		cartItemRepository.deleteById(cartItem.get().getCartItemId());
	}

}
