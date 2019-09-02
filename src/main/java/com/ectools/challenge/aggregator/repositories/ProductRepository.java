package com.ectools.challenge.aggregator.repositories;

import com.ectools.challenge.aggregator.models.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
