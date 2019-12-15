package com.example.courseJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courseJPA.entities.Product;
import com.example.courseJPA.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> findAll(){
		List<Product> listProduct = productRepository.findAll();
		return listProduct;
	}
	
	public Product findById(Integer id) {
		Optional<Product> objProduct = productRepository.findById(id);
		return objProduct.get();
	}
	
}
