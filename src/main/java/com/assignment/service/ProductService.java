package com.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.assignment.exception.CategoryNotFoundException;
import com.assignment.exception.ProductNotFoundException;
import com.assignment.model.Category;
import com.assignment.model.Product;
import com.assignment.repository.ICartItemRepository;
import com.assignment.repository.ICategoryRepository;
import com.assignment.repository.IProductRepository;
import com.assignment.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ICategoryRepository categoryRepository;
	@Autowired
	IProductRepository repository;

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return (List<Product>) repository.findAll();
	}

	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		Optional<Product> productById = repository.findById(id);
		if (productById.isPresent())
			return productById.get();
		throw new ProductNotFoundException("Product with id " + id + " is not available");
	}

	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		Optional<Category> category = categoryRepository.findById(product.getCategory().getCategoryId());
		if (category.isEmpty())
			throw new CategoryNotFoundException(
					"Category with name " + product.getCategory().getName() + " doesn't exist");
		
		repository.save(product);
	}

	public void deleteProductById(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
