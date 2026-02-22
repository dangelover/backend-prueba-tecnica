package com.pruebatesting.backend.products.infrastructure.adapter.out;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@Table("products")
public class ProductEntity {
    @Id
    private Integer id;
    private String code;
    private String name;
    private String description;
    private Double price;
    private String category;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Boolean state;
}
