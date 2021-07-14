package com.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.OrderItem;

@Repository
public interface IOrderItemRepository extends CrudRepository<OrderItem, Integer>{

}
