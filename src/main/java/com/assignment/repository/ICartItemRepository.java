package com.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.CartItem;

@Repository
public interface ICartItemRepository extends CrudRepository<CartItem, Integer> {
//	public void deleteById(int id);

}
