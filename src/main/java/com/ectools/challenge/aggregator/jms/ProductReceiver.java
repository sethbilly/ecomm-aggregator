package com.ectools.challenge.aggregator.jms;

import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductReceiver {

    private static final Logger log = LoggerFactory.getLogger(ProductReceiver.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job createProductJob;

//    @Autowired private ProductService productService;
    /**
     * On message we save or update products accordingly
     * @param receivedProducts
     */
    @JmsListener(destination = "ProductTransactionQueue", containerFactory = "connectionFactory")
    public void receiveMessage(List<Product> receivedProducts) {
        log.info(">> Received products: " + receivedProducts.toString());
        /**
         * Call createProductJob batch here to handle
         * creation and update of products
         */

        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        //jobParameterMap.put("products", new JobParameter("products", (List) receivedProducts));

        JobParameters jobParameters = new JobParameters(jobParameterMap);

        try {
            jobLauncher.run(createProductJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

//        for(Product product : receivedProducts) {
//            Optional<Product> foundProduct = productService.findById(product.getUuid());
//            if(!foundProduct.isPresent()) {
//                productService.save(product);
//            }else {
//                product.setUpdatedAt(new Date());
//                productService.update(product, product.getUuid());
//            }
//        }

    }
}
