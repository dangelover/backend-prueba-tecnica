package com.pruebatesting.backend.products.infrastructure.adapter.in;

import com.pruebatesting.backend.products.domain.model.Product;
import com.pruebatesting.backend.products.domain.port.in.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductUseCase productUseCase;
    @PostMapping
    public Mono<Product> create(@RequestBody Product product) {
        return productUseCase.create(product);
    }
    @GetMapping("{id}")
    public Mono<Product> findById(@PathVariable Integer id) {
        return productUseCase.findById(id);
    }
    @GetMapping
    public Flux<Product> findAll() {
        return productUseCase.findAll();
    }
    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        return productUseCase.update(id, product);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return productUseCase.delete(id);
    }


}
