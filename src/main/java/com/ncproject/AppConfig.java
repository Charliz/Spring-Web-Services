package com.ncproject;

import com.ncproject.repository.ProductStubService;
import com.ncproject.repository.ProductStubServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Champion on 20.03.2017.
 */

@Configuration
public class AppConfig {
    @Bean
    public ProductStubService stubService () {

        return new ProductStubServiceImpl();


    }

}
