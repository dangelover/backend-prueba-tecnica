package com.pruebatesting.backend.products.infrastructure.config;

import com.pruebatesting.backend.products.application.service.ProductService;
import com.pruebatesting.backend.products.domain.port.in.ProductUseCase;
import com.pruebatesting.backend.products.domain.port.out.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductUseCase productUseCase(ProductRepositoryPort repositoryPort) {
        return new ProductService(repositoryPort);
    }
}
