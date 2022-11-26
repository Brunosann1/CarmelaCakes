package com.sann.carmelacakes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sann.carmelacakes.model.CustomerOrder;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long>{

}
