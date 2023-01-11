package com.sann.carmelacakes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sann.carmelacakes.model.CakeOrder;

@Repository
public interface CakeOrderRepository extends CrudRepository<CakeOrder, Long>{

}
