package com.ectools.challenge.aggregator.services;

import com.ectools.challenge.aggregator.events.ProductCreatedEvent;
import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.repositories.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Date;

@Service
@Log4j2
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher publisher;

    public ProductServiceImp(ProductRepository productRepository, ApplicationEventPublisher publisher) {
        this.productRepository = productRepository;
        this.publisher = publisher;
    }

    public Flux<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public Mono<Product> findById(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Mono<Product> update(String id, Date updatedAt) {
        return this.productRepository
                .findById(id)
                .map(p -> new Product(p.getUuid(), updatedAt))
                .flatMap(this.productRepository::save);
    }

    public Mono<Product> create(Product product) {
        return this.productRepository
                .save(product)
                .doOnSuccess(product1 -> this.publisher.publishEvent(new ProductCreatedEvent(product1)));
    }
}
