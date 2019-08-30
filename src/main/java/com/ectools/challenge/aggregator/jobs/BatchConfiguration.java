package com.ectools.challenge.aggregator.jobs;

import com.ectools.challenge.aggregator.models.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ProductWriter productWriter;

    @Autowired
    public ProductReader productReader;

    @Autowired
    public ProductProcessor productProcessor;

    @Bean
    public Job createProductJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("createProductJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();

    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Product, Product> chunk(10)
                .reader(productReader)
                .processor(productProcessor)
                .writer(productWriter)
                .build();
    }


}
