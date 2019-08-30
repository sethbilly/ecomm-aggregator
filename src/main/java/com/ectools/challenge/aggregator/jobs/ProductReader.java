package com.ectools.challenge.aggregator.jobs;

import com.ectools.challenge.aggregator.models.Product;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class ProductReader implements ItemReader<Product> {

    @Value("#{jobParameter['products']}")
    List<Product> products;
    private int counter;

    public ProductReader() {
        counter = 0;
    }

    @Override
    public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        Product product = null;

        if(counter < products.size()) {
            product = products.get(counter);
            counter++;
        }
        return product;
    }
}
