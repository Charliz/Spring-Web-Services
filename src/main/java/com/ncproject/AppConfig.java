package com.ncproject;

import com.ncproject.controller.RemoteEJBClient;
import com.ncproject.webstore.ejb.ProductBeanInterface;
import com.ncproject.webstore.ejb.beans.ProductBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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

}
