package com.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Category;
import com.assignment.model.Product;

@Repository
public interface IProductRepository extends CrudRepository<Product, Integer> {
	List<Product> findByCategory(Optional<Category> category);
}
