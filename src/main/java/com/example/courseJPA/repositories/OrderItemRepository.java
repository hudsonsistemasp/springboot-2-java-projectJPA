package com.example.courseJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courseJPA.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
