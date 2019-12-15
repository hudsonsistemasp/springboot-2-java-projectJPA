package com.example.courseJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courseJPA.entities.Category;
import com.example.courseJPA.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.get();
	}
}
