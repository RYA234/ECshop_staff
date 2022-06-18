package com.example.demo.repository;

import com.example.demo.domain.model.MProduct;

class TestProductFactory {
    static MProduct create(
            String name,
            Integer price,
            String gazou
    ) {
        return MProduct.builder()
                .code(1)
                .name(name)
                .price(price)
                .gazou(gazou)
                .build();
    }
}