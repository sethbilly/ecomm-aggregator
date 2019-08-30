package com.ectools.challenge.aggregator.jobs;

import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.services.ProductService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductWriter implements ItemWriter<Product> {

    @Autowired
    private ProductService productService;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        for(Product product : products) {
            productService.update(product, product.getUuid());
        }
    }
}
