package com.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Cart;

@Repository
public interface ICartRepository extends CrudRepository<Cart, Integer> {

}
