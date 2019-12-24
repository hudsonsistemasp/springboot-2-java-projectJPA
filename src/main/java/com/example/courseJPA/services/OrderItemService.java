package com.example.courseJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.courseJPA.entities.OrderItem;
import com.example.courseJPA.repositories.OrderItemRepository;

public class OrderItemService {
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	public List<OrderItem> findAll(){
		return orderItemRepository.findAll();
	}
	
	public OrderItem findById(Integer id) {
		Optional<OrderItem> obj = orderItemRepository.findById(id);
		return obj.get();
	}
}
