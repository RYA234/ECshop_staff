package com.example.demo;

import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.ProductEditController;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductEditControllerTest {
    @Autowired
    private MockMvc productMvc;

    @Autowired
//            @MockBean
    ProductService productService;
    @MockBean
//    @Autowired
    private ProductEditController testProductController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.productMvc = MockMvcBuilders.standaloneSetup(testProductController).build();
    }


    @Test
    @DisplayName("01.商品一覧画面から商品編集画面HTTPステータスコードと遷移先ページ確認")
    public void productListToEditControllerTest1() throws Exception {
        productMvc.perform
                        (
                                MockMvcRequestBuilders.post("/product/product_list")
                                        .param(MvcStatic.Product.Edit.PARAM_PRODUCT_LIST_TO_EDIT, MvcStatic.Product.Edit.PARAM_PRODUCT_LIST_TO_EDIT)

                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("product/product_list")
                );

    }

    @Test
    @DisplayName("02.商品編集画面から商品編集確認画面HTTPステータスコードと遷移先ページ確認")
    public void productEditToCheckControllerTest() throws Exception {
        productMvc.perform
                        (
                                MockMvcRequestBuilders.post("/product/product_edit_check")
                                        .param( MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("product/product_edit_check")
                );

    }
    @Test
    @DisplayName("03.商品編集確認画面から商品編集画面HTTPステータスコードと遷移先ページ確認")
    public void productEditCheckBackControllerTest() throws Exception {
        productMvc.perform
                        (
                                MockMvcRequestBuilders.post("/product/product_edit")
                                        .param( MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("product/product_edit")
                );

    }
    @Test
    @DisplayName("04.商品編集確認画面から商品編集完了画面HTTPステータスコードと遷移先ページ確認")
    public void productCheckToDoneControllerTest() throws Exception {
        productMvc.perform
                        (
                                MockMvcRequestBuilders.post("/product/product_edit_done")
                                        .param( MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("product/product_edit_done")
                );

    }


}