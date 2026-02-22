package com.pruebatesting.backend.products.infrastructure.adapter.out;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Integer> {
}
