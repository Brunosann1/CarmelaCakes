package com.sann.carmelacakes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sann.carmelacakes.model.Cake;

@Repository
public interface CakeRepository extends CrudRepository<Cake, Long>{

}
