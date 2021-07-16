package com.assignment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exception.CustomerNotFoundException;
import com.assignment.model.Cart;
import com.assignment.model.CartItem;
import com.assignment.model.Order;
import com.assignment.repository.ICartItemRepository;
import com.assignment.repository.ICartRepository;
import com.assignment.repository.IOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	@Autowired
	IOrderRepository orderRepository;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	CartItemService cartItemService;

	@Autowired
	ICartItemRepository cartItemRepository;
	
	@Autowired
	ICartRepository cartRepository;

	public Order createOrder(Cart cart) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setCustomerId(cart.getCustomerId());
		Date date = new Date();
		order.setDateOfOrder(date);
		order.setTotalAmount(cart.totalAmount());
		Order insertedOrder = orderRepository.save(order);
		cart.getCartItems().stream().forEach(item -> {
			orderItemService.createOrderItem(item, insertedOrder);
//			cartItemService.deleteByCartItemId(item.getCartItemId());
//			System.out.println("passing cartItem ID : " + item.getCartItemId());
		});
//		cart.getCartItems().stream().forEach(item -> {
//			cartItemService.deleteByCartItemId(item.getCartItemId());
//			log.info("cartItem with ID :{} deleted",item.getCartItemId());
//		});

		Cart newCart = new Cart();
		newCart.setCartId(cart.getCartId());
		newCart.setCustomerId(cart.getCustomerId());
		cartRepository.deleteById(cart.getCartId());
		cartRepository.save(newCart);
		return order;
	}

	public List<Order> getAllOrdersByUserId(int customerId) {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepository.findByCustomerId(customerId);
		return orders;
	}

	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return (List<Order>) orderRepository.findAll();
	}

}
