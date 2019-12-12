package com.example.courseJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courseJPA.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
