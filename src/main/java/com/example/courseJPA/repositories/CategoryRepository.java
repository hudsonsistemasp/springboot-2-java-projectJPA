package com.example.courseJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.courseJPA.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
