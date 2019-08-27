package com.ectools.challenge.aggregator.components;

import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTransactionReceiver {

    @Autowired private ProductRepository productRepository;

    @JmsListener(destination = "ProductTransactionQueue", containerFactory = "defaultFactory")
    public void receiveMessage(List<Product> products) {

        /***
         * Check if product already exist using uuid and update
         * else create new product record
         */
        productRepository.saveAll(products);
    }
}
