package com.ncproject.repository;

import com.ncproject.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Champion on 20.03.2017.
 */

public class ProductStubServiceImpl implements ProductStubService {

    private static final AtomicLong counter = new AtomicLong();
    private static List<Product> products;

    static {
        products = fillInProducts();
    }

    private static List<Product> fillInProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(counter.incrementAndGet(), "Apple", new BigDecimal (550),
                "Awesome phone", "iPhone7", 300));

        products.add(new Product(counter.incrementAndGet(), "Xiaomi", new BigDecimal (370),
                "Great phone! The best!", "Xiaomi Mi5", 250));

        products.add(new Product(counter.incrementAndGet(), "Samsung", new BigDecimal (770),
                "Super TV", "New Samsung TV", 200));

        products.add(new Product(counter.incrementAndGet(), "Samsung", new BigDecimal (400),
                "Nice phone", "Galaxy 7 phone", 480));

        products.add(new Product(counter.incrementAndGet(), "Apple", new BigDecimal (870),
                "Very good product", "iPad", 137));

        return products;
    }

    @Override
    public List<Product> findAllProducts() {
        return products;
    }

    @Override
    public Product findById(long id) {
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        product.setId(counter.incrementAndGet());
        products.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        int index = products.indexOf(product);
        products.set(index, product);
    }

    @Override
    public void deleteProduct(long id) {
        for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
            Product product = iterator.next();
            if (product.getId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean isProductExist(Product product) {
        return findByName(product.getBrand())!=null;
    }

    @Override
    public Product findByName(String name) {
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

}
