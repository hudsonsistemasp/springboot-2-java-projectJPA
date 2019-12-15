package com.example.courseJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courseJPA.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
