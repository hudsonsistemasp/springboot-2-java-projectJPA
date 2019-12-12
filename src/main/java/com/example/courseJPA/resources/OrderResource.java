package com.example.courseJPA.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.courseJPA.entities.Order;
import com.example.courseJPA.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value ="/{id}" )
	public ResponseEntity<Order> findById(@PathVariable Integer id) {
		Order order = orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}
	

}
