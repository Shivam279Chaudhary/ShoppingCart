package com.assignment.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.assignment.model.Category;
import com.assignment.model.Product;

@Repository
public class ProductRepository {

	List<Product> products;

//	@PostConstruct
//    public void populateInitialData(){
//		this.products = new ArrayList<>();
//        this.products.add(new Product(1, "product-1", 10, new Category("electronice"));
//        this.products.add(new Product(2, "product-2", 5, 2));
//        this.products.add(new Product(3, "product-3", 10, 1));
//        this.products.add(new Product(4, "product-4", 20, 3));
//    }
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return this.products;
	}

}
