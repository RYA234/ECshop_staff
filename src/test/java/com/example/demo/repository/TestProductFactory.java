package com.example.demo.repository;

import com.example.demo.domain.model.MProduct;

class TestProductFactory {
    static MProduct create(
            Integer code,
            String name,
            Integer price,
            String gazou
    ) {
        return MProduct.builder()
                .code(code)
                .name(name)
                .price(price)
                .gazou(gazou)
                .build();
    }
}