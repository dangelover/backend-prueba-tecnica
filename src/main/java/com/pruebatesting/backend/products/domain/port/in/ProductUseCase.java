package com.pruebatesting.backend.products.domain.port.in;

import com.pruebatesting.backend.products.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductUseCase {
    Mono<Product> create(Product product);
    Mono<Product> findById(Integer id);
    Flux<Product> findAll();
    Mono<Product> update(Integer id, Product product);
    Mono<Void> delete(Integer id);
}
