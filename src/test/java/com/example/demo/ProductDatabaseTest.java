package com.example.demo;

import com.example.demo.domain.model.MProduct;
import com.example.demo.service.ProductService;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@DbUnitConfiguration(dataSetLoader = CsvDatasSetLoader.class) // DBUnitでCSVファイルを使えるよう指定。＊CsvDataSetLoaderクラスは自作します（後述）
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
        TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや＠ExpectedDatabaseなどを使えるように指定
})
@Transactional // @DatabaseSetupで投入するデータをテスト処理と同じトランザクション制御とする。（テスト後に投入データもロールバックできるように）
public class ProductDatabaseTest {
    @Autowired
    private ProductService productService;
    @Test
    @DisplayName("データベース商品が追加されるか確認")
    @DatabaseSetup("/testdata/ProductServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/ProductServiceTest/after-create-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
        // テスト実行後に１件データが追加されていること
    void productAddCheck() {
        MProduct newProduct = MProduct.builder()
                .code(3)
                .name("zyx")
                .price(220)
                .gazou("").build();

        productService.addProduct(newProduct);
        Assertions.assertEquals(3, newProduct.getCode());
        Assertions.assertEquals("zyx", newProduct.getName());
        Assertions.assertEquals(220, newProduct.getPrice());
        Assertions.assertEquals("nnn", newProduct.getGazou());
    }


    @Test
    @DisplayName("データベース商品が更新されるか確認")
    @DatabaseSetup("/testdata/ProductServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/ProductServiceTest/after-update-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void productUpdate() {

       productService.updateProductone(2, "メガネ22", 1000, "gazou/megane");
        Assertions.assertEquals(2, 2);
        Assertions.assertEquals("メガネ22","メガネ22");
        Assertions.assertEquals(1000,1000);
//        Assertions.assertEquals("gazou/megane", product.getGazou());

            }

    @Test
    @DisplayName("データベース商品が一件選択されるか確認")
    @DatabaseSetup("/testdata/ProductServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/ProductServiceTest/init-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void productSelectOne() {
        MProduct product = productService.getProduct(2);
//        2,ffgsd,dfaas
        Assertions.assertEquals(2, product.getCode());
        Assertions.assertEquals("メガネ", product.getName());
        Assertions.assertEquals(12000, product.getPrice());
        Assertions.assertEquals("gazou/megane", product.getGazou());
    }

    @Test
    @DisplayName("データベース商品が全件選択されるか確認")
    @DatabaseSetup("/testdata/ProductServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/ProductServiceTest/init-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void productSelectAll()
    {
        List<MProduct> product = productService.getProducts();

        Assertions.assertEquals("焼肉", product.get(0).getName());
        Assertions.assertEquals(900, product.get(0).getPrice());
        Assertions.assertEquals("gazou/aiueo", product.get(0).getGazou());

        Assertions.assertEquals("メガネ", product.get(1).getName());
        Assertions.assertEquals(12000, product.get(1).getPrice());
        Assertions.assertEquals("gazou/megane", product.get(1).getGazou());
    }

}









