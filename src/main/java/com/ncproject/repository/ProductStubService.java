package com.ncproject.repository;

import com.ncproject.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Champion on 20.03.2017.
 */

public interface ProductStubService {

    List<Product> findAllProducts();
    Product findById(long id);
    void addProduct(Product product);
    boolean isProductExist(Product product);
    Product findByName(String name);
    void updateProduct(Product product);
    void deleteProduct(long id);



}
