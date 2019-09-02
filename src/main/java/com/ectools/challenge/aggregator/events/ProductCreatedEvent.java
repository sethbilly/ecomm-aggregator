package com.ectools.challenge.aggregator.events;

import com.ectools.challenge.aggregator.models.Product;
import org.springframework.context.ApplicationEvent;

public class ProductCreatedEvent extends ApplicationEvent {

    public ProductCreatedEvent(Product source) {
        super(source);
    }
}
