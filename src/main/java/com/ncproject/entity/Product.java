package com.ncproject.entity;

import java.math.BigDecimal;

/**
 * Created by Champion on 20.03.2017.
 */
public class Product {

    private long id;
    private String description;
    private String productName;
    private BigDecimal price;
    private String brand;
    private int quantity;

    public Product() {
        id =0;
    }

    public Product(long id, String brand, BigDecimal price, String description, String productName, int quantity) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
