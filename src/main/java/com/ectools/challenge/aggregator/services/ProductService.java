package com.ectools.challenge.aggregator.services;

import com.ectools.challenge.aggregator.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Mono<Product> create(Product product);
    Flux<Product> getProducts();
    Mono<Product> findById(String id);
    Mono<Product> update(String id, Date updatedAt);

}
