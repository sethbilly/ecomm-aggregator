package com.ectools.challenge.aggregator.api;

import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aggregator")
public class AggregatorController {

    @Autowired private ProductRepository productRepository;

    @GetMapping("/products")
    @ResponseBody
    public List<Product> allProducts() {
        return (List)productRepository.findAll();
    }

    @GetMapping("/daily-stats")
    @ResponseBody
    public void dailyStats() {

    }
}
