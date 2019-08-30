package com.ectools.challenge.aggregator.jobs;

import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.services.ProductService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ProductProcessor implements ItemProcessor<Product, Product> {

    @Autowired
    private ProductService productService;

    @Override
    public Product process(Product product) throws Exception {
        Optional<Product> foundProduct = productService.findById(product.getUuid());
        if(foundProduct.isPresent()) {
            product.setUpdatedAt(new Date());
        }
        return product;
    }
}
