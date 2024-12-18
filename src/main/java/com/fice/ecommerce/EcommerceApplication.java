package com.fice.ecommerce;

import com.fice.ecommerce.data.CategoryRepository;
import com.fice.ecommerce.data.CustomerRepository;
import com.fice.ecommerce.data.OrderRepository;
import com.fice.ecommerce.data.ProductRepository;
import com.fice.ecommerce.data.mock.impl.InMemoryCategoryRepositoryImpl;
import com.fice.ecommerce.data.mock.impl.InMemoryCustomerRepositoryImpl;
import com.fice.ecommerce.data.mock.impl.InMemoryOrderRepositoryImpl;
import com.fice.ecommerce.data.mock.impl.InMemoryProductRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
