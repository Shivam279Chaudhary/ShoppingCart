package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Order;
import com.assignment.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;

	@GetMapping("/customers/{customerId}/orders")
	public ResponseEntity<List<Order>> getOrdersOfUser(@PathVariable int customerId){
		List<Order> orders = orderService.getAllOrdersByUserId(customerId);
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
}
