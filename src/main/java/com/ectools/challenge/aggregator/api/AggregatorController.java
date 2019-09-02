package com.ectools.challenge.aggregator.api;

import com.ectools.challenge.aggregator.models.DailyStats;
import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.services.ProductService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/aggregator", produces = MediaType.APPLICATION_JSON_VALUE)
public class AggregatorController {

    private final ProductService productService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;

    public AggregatorController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    Flux<Product> allProducts() {
        return productService.getProducts();
    }

    @PostMapping("/create")
    Publisher<ResponseEntity<Product>> create(@RequestBody Product profile) {
        return this.productService
                .create(profile)
                .map(p -> ResponseEntity.created(URI.create("/profiles/" + p.getUuid()))
                        .contentType(mediaType)
                        .build());
    }

    @GetMapping("/{id}")
    Publisher<Product> getById(@PathVariable("id") String id) {
        return this.productService.findById(id);
    }

    @GetMapping("/daily-stats")
    Publisher<DailyStats> dailyStats() {
        return null;
    }
}
