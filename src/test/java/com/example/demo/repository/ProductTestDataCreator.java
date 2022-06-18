package com.example.demo.repository;

import com.example.demo.domain.model.MProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ProductTestDataCreator {

    @Autowired
    private ECShopMapper ecShopMapper;

    void create(
            Integer code,
            String name,
            Integer price,
            String gazou
    ) {
        MProduct product = TestProductFactory.create(code, name, price, gazou);
        ecShopMapper.productInsertOne(product);
    }
}