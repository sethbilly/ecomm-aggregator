package com.ectools.challenge.aggregator.api;

import com.ectools.challenge.aggregator.models.DailyStats;
import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aggregator")
public class AggregatorController {

    @Autowired private ProductService productService;

    @GetMapping("/products")
    public @ResponseBody Iterable<Product> allProducts() {
        return productService.getProducts();
    }

    @GetMapping("/daily-stats")
    public @ResponseBody List<DailyStats> dailyStats() {
        return new ArrayList<>();
    }
}
