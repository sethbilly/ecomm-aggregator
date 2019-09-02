package com.ectools.challenge.aggregator.jms;
import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ProductReceiver {

    private static final Logger log = LoggerFactory.getLogger(ProductReceiver.class);

    private ProductService productService;


    public ProductReceiver(ProductService productService) {
        this.productService = productService;
    }
    /**
     * On message we save or update products accordingly
     * @param receivedProducts
     */
    @JmsListener(destination = "ProductTransactionQueue", containerFactory = "connectionFactory")
    public void receiveMessage(List<Product> receivedProducts) {

        RestTemplate restTemplate = new RestTemplate();
        for(Product product : receivedProducts) {
            HttpEntity<Product> toSave = new HttpEntity<>(product);
            restTemplate.postForObject("http://localhost:8085/aggregator/create", toSave,Product.class);
        }

    }

}
