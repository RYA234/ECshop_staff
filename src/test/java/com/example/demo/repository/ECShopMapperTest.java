package com.example.demo.repository;

import com.example.demo.domain.model.MProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
class ECShopMapperTest {

    @Autowired
    private ECShopMapper ecShopMapper;

    @Autowired
    private ProductTestDataCreator productTestDataCreator;

    @Test
    void shouldGetProductByCode() {
        productTestDataCreator.create("tanaka taro", 1000, "file/path");

        MProduct fetchedProduct = ecShopMapper.productFindOne(1);

        assertEquals(fetchedProduct.getCode(), 1);
        assertEquals(fetchedProduct.getName(), "tanaka taro");
        assertEquals(fetchedProduct.getPrice(), 1000);
        assertEquals(fetchedProduct.getGazou(), "file/path");
    }

    // FIXME : メソッド名は修正する - プロダクト取得時に、渡したcodeに対応したレコードが存在しないとき、nullを返す。
    @Test
    void shouldGetProductByCodeWhenNotResource() {
        MProduct fetchedProduct = ecShopMapper.productFindOne(1);

        assertNull(fetchedProduct);
    }
}



