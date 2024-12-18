package com.fice.ecommerce.config;

import com.fice.ecommerce.data.CategoryRepository;
import com.fice.ecommerce.data.CustomerRepository;
import com.fice.ecommerce.data.OrderRepository;
import com.fice.ecommerce.data.ProductRepository;
import com.fice.ecommerce.data.mock.impl.InMemoryCategoryRepositoryImpl;
import com.fice.ecommerce.data.mock.impl.InMemoryCustomerRepositoryImpl;
import com.fice.ecommerce.data.mock.impl.InMemoryOrderRepositoryImpl;
import com.fice.ecommerce.data.mock.impl.InMemoryProductRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
    @Bean
    OrderRepository orderRepository() {
        return new InMemoryOrderRepositoryImpl();
    }
    
    @Bean
    CategoryRepository categoryRepository() {
        return new InMemoryCategoryRepositoryImpl();
    }
    
    @Bean
    @Scope("prototype")
    ProductRepository productRepository() {
        return new InMemoryProductRepositoryImpl();
    }
    
    @Bean
    @Scope("singleton")
    CustomerRepository customerRepository() {
        return new InMemoryCustomerRepositoryImpl();
    }
}
