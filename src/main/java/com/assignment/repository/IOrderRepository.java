package com.assignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Order;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Integer>{
	List<Order> findByCustomerId(int customerId);
}
