package com.example.courseJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courseJPA.entities.Order;
import com.example.courseJPA.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
		
		public List<Order> findAll(){
			return orderRepository.findAll();
		}
		
		public Order findById(Integer id) {
			Optional<Order> objOrder = orderRepository.findById(id);
			return objOrder.get();
		}
	

}
