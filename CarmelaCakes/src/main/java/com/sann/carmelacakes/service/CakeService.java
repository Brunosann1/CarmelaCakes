package com.sann.carmelacakes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sann.carmelacakes.model.Cake;
import com.sann.carmelacakes.repository.CakeRepository;

@Service
public class CakeService {
	@Autowired
	private CakeRepository cakeRepository;
	
	public Cake save(Cake cake) {
		return cakeRepository.save(cake);
	}
}
