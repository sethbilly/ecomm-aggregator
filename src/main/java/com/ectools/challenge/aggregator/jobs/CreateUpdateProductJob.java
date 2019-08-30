package com.ectools.challenge.aggregator.jobs;


import com.ectools.challenge.aggregator.models.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class CreateUpdateProductJob extends JobExecutionListenerSupport {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    ProductProcessor productProcessor;

    @Autowired
    ProductWriter productWriter;

    @Autowired
    ProductReader productReader;

    @Bean
    public Job productJob() {
        Step step = stepBuilderFactory.get("step-1")
                .<Product, Product> chunk(1)
                .reader(productReader)
                .processor(productProcessor)
                .writer(productWriter)
                .build();

        Job job = jobBuilderFactory.get("productJob")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();

        return job;
    }
}
