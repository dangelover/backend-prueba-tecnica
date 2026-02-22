package com.pruebatesting.backend;

import com.pruebatesting.backend.products.application.service.ProductService;
import com.pruebatesting.backend.products.domain.exception.ProductNotFoundException;
import com.pruebatesting.backend.products.domain.model.Product;
import com.pruebatesting.backend.products.domain.port.out.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepositoryPort repositoryPort;
    @InjectMocks
    private ProductService productService;
    @Test
    void shouldReturnProductWhenExists() {

        Product product = Product.builder()
                .id(1)
                .code("P001")
                .name("Laptop")
                .description("Gaming laptop")
                .price(1000.0)
                .category("Electronics")
                .regDate(LocalDateTime.now())
                .modDate(LocalDateTime.now())
                .state(true)
                .build();

        when(repositoryPort.findById(1))
                .thenReturn(Mono.just(product));

        Mono<Product> result = productService.findById(1);

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();

        verify(repositoryPort).findById(1);
    }

    @Test
    void shouldThrowExceptionWhenProductNotExists() {

        when(repositoryPort.findById(1))
                .thenReturn(Mono.empty());

        Mono<Product> result = productService.findById(1);

        StepVerifier.create(result)
                .expectError(ProductNotFoundException.class)
                .verify();

        verify(repositoryPort).findById(1);
    }
    @Test
    void shouldUpdateProductWhenExists() {

        Integer id = 1;

        Product existingProduct = Product.builder()
                .id(id)
                .code("P001")
                .name("Old Name")
                .description("Old Description")
                .price(100.0)
                .category("Old Category")
                .regDate(LocalDateTime.now())
                .modDate(LocalDateTime.now())
                .state(true)
                .build();

        Product updatedData = Product.builder()
                .name("New Name")
                .description("New Description")
                .price(200.0)
                .category("New Category")
                .build();

        Product savedProduct = Product.builder()
                .id(id)
                .code("P001")
                .name("New Name")
                .description("New Description")
                .price(200.0)
                .category("New Category")
                .regDate(existingProduct.getRegDate())
                .modDate(LocalDateTime.now())
                .state(true)
                .build();

        when(repositoryPort.findById(id))
                .thenReturn(Mono.just(existingProduct));

        when(repositoryPort.save(any(Product.class)))
                .thenReturn(Mono.just(savedProduct));

        Mono<Product> result = productService.update(id, updatedData);

        StepVerifier.create(result)
                .expectNextMatches(product ->
                        product.getName().equals("New Name") &&
                                product.getPrice().equals(200.0) &&
                                product.getCategory().equals("New Category")
                )
                .verifyComplete();

        verify(repositoryPort).findById(id);
        verify(repositoryPort).save(any(Product.class));
    }



}
