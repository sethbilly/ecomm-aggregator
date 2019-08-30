package com.ectools.challenge.aggregator.jms;

import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductReceiver {

    private static final Logger log = LoggerFactory.getLogger(ProductReceiver.class);

    @Autowired private ProductService productService;
    /**
     * On message we save or update products accordingly
     * @param receivedProducts
     */
    @JmsListener(destination = "ProductTransactionQueue", containerFactory = "connectionFactory")
    public void receiveMessage(List<Product> receivedProducts) {
        log.info(">> Received products: " + receivedProducts.toString());
        for(Product product : receivedProducts) {
            Optional<Product> foundProduct = productService.findById(product.getUuid());
            if(!foundProduct.isPresent()) {
                productService.save(product);
            }else {
                product.setUpdatedAt(new Date());
                productService.update(product, product.getUuid());
            }
        }

    }
}
