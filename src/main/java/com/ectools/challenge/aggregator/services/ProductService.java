package com.ectools.challenge.aggregator.services;

import com.ectools.challenge.aggregator.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public void createProduct(Product product);
    public void createAllProducts(List<Product> products);
    public List<Product> getProducts();
    public Optional<Product> findById(String uuid);
    public Product update(Product product, String uuid);

}
