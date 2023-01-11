package com.sann.carmelacakes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sann.carmelacakes.model.CakeOrder;
import com.sann.carmelacakes.service.CakeOrderService;

@RestController
@RequestMapping("/order")
public class CakeOrderController {

	@Autowired
	CakeOrderService cakeOrderService;

	@GetMapping("/all")
	public Iterable<CakeOrder> allOrders() {
		return cakeOrderService.findAll();
	}

	@PostMapping("/new")
	public ResponseEntity<CakeOrder> newOrder(@RequestBody CakeOrder cakeOrder) {
		if (cakeOrderService.newCakeOrder(cakeOrder) != null) {
			return new ResponseEntity<CakeOrder>(cakeOrder, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<CakeOrder>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
