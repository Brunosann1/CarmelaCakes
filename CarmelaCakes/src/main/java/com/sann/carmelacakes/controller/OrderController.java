package com.sann.carmelacakes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sann.carmelacakes.model.CustomerOrder;
import com.sann.carmelacakes.repository.OrderRepository;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderRepository cakeOrderRepository;

	@GetMapping("/all")
	public Iterable<CustomerOrder> allOrders() {
		return cakeOrderRepository.findAll();
	}
}
