package com.pruebatesting.backend.products.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Product {
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
