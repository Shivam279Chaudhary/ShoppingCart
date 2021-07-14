package com.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Category;

@Repository
public interface ICategoryRepository extends CrudRepository<Category, Integer>{
}
