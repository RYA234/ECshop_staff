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

    @Test
    void shouldGetProductByCode() {
        // FIXME : テストデータ用のファクトリクラスを作成して処理を移す
        MProduct createdProduct = MProduct.builder()
                .code(1)
                .name("tanaka taro")
                .price(1000)
                .gazou("file/path")
                .build();
        ecShopMapper.productInsertOne(createdProduct);

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