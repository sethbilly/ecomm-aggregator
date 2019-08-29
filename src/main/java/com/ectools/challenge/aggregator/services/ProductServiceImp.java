package com.ectools.challenge.aggregator.services;

import com.ectools.challenge.aggregator.models.Product;
import com.ectools.challenge.aggregator.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void createAllProducts(List<Product> products) {
        productRepository.saveAll((Iterable) products);
    }

    @Override
    public List<Product> getProducts() {
        return (List) productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(String uuid) {
        return productRepository.findById(uuid);
    }

    @Override
    public Product update(Product product, String uuid) {
        return productRepository.save(product);
    }
}
