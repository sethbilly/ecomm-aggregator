package com.ectools.challenge.aggregator.repositories;

import com.ectools.challenge.aggregator.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

}
