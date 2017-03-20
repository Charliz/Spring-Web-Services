package com.ncproject.controller;

import com.ncproject.entity.Product;
import com.ncproject.repository.ProductStubService;
import com.ncproject.utils.CustomError;
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
    ProductStubService productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") long id) {

        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity(new CustomError("Product with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {

        productService.addProduct(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/webstore/product/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {

        Product currentProduct = productService.findById(id);

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
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {

        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity(new CustomError("Unable to delete. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }




}
