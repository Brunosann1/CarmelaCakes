package com.sann.carmelacakes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sann.carmelacakes.model.CustomerOrder;
import com.sann.carmelacakes.repository.CakeRepository;
import com.sann.carmelacakes.repository.CustomerOrderRepository;
import com.sann.carmelacakes.repository.CustomerRepository;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	CustomerOrderRepository customerOrderRepository;
	@Autowired
	CakeRepository cakeRepository;
	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/all")
	public Iterable<CustomerOrder> allOrders() {
		return customerOrderRepository.findAll();
	}
	
	
	@PostMapping("/new")
	public ResponseEntity<CustomerOrder> newOrder(@RequestBody CustomerOrder newOrderRequest) {
		
		cakeRepository.save(newOrderRequest.getCake());
		customerRepository.save(newOrderRequest.getCustomer());
		CustomerOrder newOrder = customerOrderRepository.save(newOrderRequest);
		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}
}
