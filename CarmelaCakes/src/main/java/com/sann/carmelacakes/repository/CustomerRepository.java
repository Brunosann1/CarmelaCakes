package com.sann.carmelacakes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sann.carmelacakes.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
