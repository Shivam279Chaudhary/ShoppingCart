package com.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exception.CategoryNotFoundException;
import com.assignment.model.Category;
import com.assignment.model.Product;
import com.assignment.repository.ICategoryRepository;
import com.assignment.repository.IProductRepository;

@Service
public class CategoryService {
	@Autowired
	ICategoryRepository repository;
	@Autowired
	IProductRepository productRepository;

	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = (List<Category>) repository.findAll();
		return categories;
	}

	public List<Product> getAllProductsByCategoryId(int id) {
		// TODO Auto-generated method stub
		Optional<Category> category = repository.findById(id);
		if(category.isEmpty())
			throw new CategoryNotFoundException("There is no Category with id: "+id+" present");
		
		List<Product> products = productRepository.findByCategory(category);
		return products;
	}

	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return repository.save(category).getCategoryId();
	}

}
