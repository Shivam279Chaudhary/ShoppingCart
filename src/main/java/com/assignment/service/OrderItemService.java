package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.model.CartItem;
import com.assignment.model.Order;
import com.assignment.model.OrderItem;
import com.assignment.repository.IOrderItemRepository;

@Service
public class OrderItemService {
	@Autowired
	IOrderItemRepository orderItemRespository;

	public OrderItem createOrderItem(CartItem cartItem, Order insertedOrder) {
		// TODO Auto-generated method stub
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(insertedOrder);
		orderItem.setPricePerUnit(cartItem.getPricePerUnit());
		orderItem.setProductId(cartItem.getProductId());
		orderItem.setQuantity(cartItem.getQuantity());
		return orderItemRespository.save(orderItem);
	}

}
