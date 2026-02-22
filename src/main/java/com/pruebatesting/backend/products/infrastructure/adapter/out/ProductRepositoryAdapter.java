package com.pruebatesting.backend.products.infrastructure.adapter.out;

import com.pruebatesting.backend.products.domain.model.Product;
import com.pruebatesting.backend.products.domain.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductRepository repository;

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(toEntity(product))
                .map(this::toDomain);
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Product> findAll() {
        return repository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return repository.deleteById(id);
    }
    private Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .state(entity.getState())
                .build();
    }
    private ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .regDate(product.getRegDate())
                .modDate(product.getModDate())
                .state(product.getState())
                .build();
    }
}
