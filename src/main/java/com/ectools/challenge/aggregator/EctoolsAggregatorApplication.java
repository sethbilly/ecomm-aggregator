package com.ectools.challenge.aggregator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.jms.ConnectionFactory;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableJms
public class EctoolsAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EctoolsAggregatorApplication.class, args);
    }
    @Bean
    public JmsListenerContainerFactory<?> connectionFactory (
           ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);

        factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction"));
        return factory;
    }

}
