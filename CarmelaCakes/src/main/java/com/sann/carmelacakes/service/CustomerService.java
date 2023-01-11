package com.sann.carmelacakes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sann.carmelacakes.model.Customer;
import com.sann.carmelacakes.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
		
	}	
}
