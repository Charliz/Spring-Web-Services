package com.ncproject;

import com.ncproject.controller.RemoteEJBClient;
import com.ncproject.repository.ProductStubService;
import com.ncproject.repository.ProductStubServiceImpl;
import com.ncproject.webstore.ejb.ProductBeanInterface;
import com.ncproject.webstore.ejb.beans.ProductBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.NamingException;

/**
 * Created by Champion on 20.03.2017.
 */

@Configuration
public class AppConfig {
    @Bean
    public ProductBeanInterface stubService () throws NamingException {

        // Add any necessary implementation
        return RemoteEJBClient.getBeanInterface(new ProductBean());


    }

    @Bean
    public ProductStubService stubService2 () throws NamingException {

        // Add any necessary implementation
        return new ProductStubServiceImpl();


    }

}
