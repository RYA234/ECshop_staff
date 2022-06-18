package com.example.demo.repository;

import com.example.demo.domain.model.MProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ECShopMapperTest {

    @Autowired
    private ECShopMapper ecShopMapper;

    @Autowired
    private ProductTestDataCreator productTestDataCreator;

    @Test
    void shouldGetProductByCode() {
        productTestDataCreator.create(1, "tanaka taro", 1000, "file/path");

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

    @Test
    void shouldGetProducts() {
        MProduct fetchedProductCode1 = TestProductFactory.create(1, "tanaka taro", 1000, "file/path");
        MProduct fetchedProductCode10 = TestProductFactory.create(10, "tanaka taro", 1000, "file/path");
        MProduct fetchedProductCode100 = TestProductFactory.create(100, "tanaka taro", 1000, "file/path");
        productTestDataCreator.create(1, "tanaka taro", 1000, "file/path");
        productTestDataCreator.create(10, "tanaka taro", 1000, "file/path");
        productTestDataCreator.create(100, "tanaka taro", 1000, "file/path");

        List<MProduct> fetchedProducts = ecShopMapper.productFindMany();

        assertEquals(fetchedProducts.size(), 3);
        assertTrue(fetchedProducts.contains(fetchedProductCode1));
        assertTrue(fetchedProducts.contains(fetchedProductCode10));
        assertTrue(fetchedProducts.contains(fetchedProductCode100));
    }

    @Test
    void shouldUpdateProduct() {
        productTestDataCreator.create(1, "tanaka taro", 1000, "file/path");
        MProduct product = TestProductFactory.create(1, "yamada hiroyuki", 1200, "file/to/path");

        ecShopMapper.productUpdate(
                product.getCode(),
                product.getName(),
                product.getPrice(),
                product.getGazou()
        );

        MProduct updatedProduct = ecShopMapper.productFindOne(product.getCode());
        assertEquals(updatedProduct.getCode(), 1);
        assertEquals(updatedProduct.getName(), "yamada hiroyuki");
        assertEquals(updatedProduct.getPrice(), 1200);
        assertEquals(updatedProduct.getGazou(), "file/to/path");
    }
}



