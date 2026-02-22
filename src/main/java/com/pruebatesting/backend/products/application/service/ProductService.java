package com.pruebatesting.backend.products.application.service;

import com.pruebatesting.backend.products.domain.exception.ProductNotFoundException;
import com.pruebatesting.backend.products.domain.model.Product;
import com.pruebatesting.backend.products.domain.port.in.ProductUseCase;
import com.pruebatesting.backend.products.domain.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductService implements ProductUseCase {
    private final ProductRepositoryPort repositoryPort;

    @Override
    public Mono<Product> create(Product product) {
        return repositoryPort.save(product);
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)));
    }

    @Override
    public Flux<Product> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Mono<Product> update(Integer id, Product product) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .flatMap(existing-> {
                    existing.setName(product.getName());
                    existing.setPrice(product.getPrice());
                    existing.setCategory(product.getCategory());
                    existing.setDescription(product.getDescription());
                    existing.setModDate(java.time.LocalDateTime.now());
                    return repositoryPort.save(existing);
                });
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .then(repositoryPort.deleteById(id));
    }
}
