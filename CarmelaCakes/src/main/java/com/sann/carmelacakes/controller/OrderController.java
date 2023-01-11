package com.sann.carmelacakes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sann.carmelacakes.model.Order;
import com.sann.carmelacakes.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService cakeOrderService;

	@GetMapping("/all")
	public Iterable<Order> allOrders() {
		return cakeOrderService.findAll();
	}

	@PostMapping("/new")
	public ResponseEntity<Order> newOrder(@RequestBody Order cakeOrder) {
		if (cakeOrderService.newCakeOrder(cakeOrder) != null) {
			return new ResponseEntity<Order>(cakeOrder, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
