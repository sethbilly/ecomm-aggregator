package com.ectools.challenge.aggregator.jms;

import com.ectools.challenge.aggregator.models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ProductMessageConverter implements MessageConverter {

    private static final Logger log = LoggerFactory.getLogger(ProductMessageConverter.class);
    ObjectMapper objectMapper;

    public ProductMessageConverter() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object obj, Session session) throws JMSException, MessageConversionException {
        List<Product> product = (List<Product>) obj;
        String payLoad = null;
        try {
            payLoad = objectMapper.writeValueAsString(product);
            log.info("Outbound json='{}'", payLoad);
        } catch (JsonProcessingException e) {
            log.error("Error converting from Product", e);
        }
        TextMessage message = session.createTextMessage();
        message.setText(payLoad);
        return message;
    }

    @Override
    public List<Product> fromMessage(Message message) throws JMSException, MessageConversionException {
        TextMessage textMessage = (TextMessage) message;
        String payLoad = textMessage.getText();
        log.info("Inbound json='{}'", payLoad);

        List<Product> products = null;
        try {
            products = objectMapper.readValue(payLoad, new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            log.error("Error converting to Product", e);
        }
        return products;
    }
}
