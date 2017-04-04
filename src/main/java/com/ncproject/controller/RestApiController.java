package com.ncproject.controller;

import com.ncproject.utils.CustomError;
import com.ncproject.webstore.ejb.ProductBeanInterface;
import com.ncproject.webstore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Champion on 20.03.2017.
 */

@RestController
@RequestMapping("/webstore")
public class RestApiController {

    @Autowired
    ProductBeanInterface productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity getProductById(@PathVariable("id") int id) {

        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity(new CustomError("Product with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {

        productService.createProduct(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/webstore/product/{id}").buildAndExpand(product.getProd_id()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {

        Product currentProduct = productService.getProductById(id);

        if (currentProduct == null) {
            return new ResponseEntity(new CustomError("Unable to upate. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentProduct.setProductName(product.getProductName());
        currentProduct.setBrand(product.getBrand());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setQuantity(product.getQuantity());

        productService.updateProduct(currentProduct);
        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {

        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity(new CustomError("Unable to delete. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }




}
