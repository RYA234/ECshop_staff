package com.example.demo;


import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.ProductEditController;
import com.example.demo.form.ProductListForm;
import com.example.demo.service.ProductService;
import com.example.demo.storage.StorageService;
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
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductEditControllerModelTest {
    @Autowired
    private MockMvc productMvc;

    @InjectMocks
    private ProductEditController testProductController;
    @Mock
//            @MockBean
    ProductService productService;

    @Mock
    StorageService storageService;
//    @Autowired


    ProductListForm productListForm = new ProductListForm();

    @BeforeEach
    void setup() {
        MultipartFile multipartFile = null;
        MockitoAnnotations.initMocks(this);
        this.productMvc = MockMvcBuilders.standaloneSetup(testProductController).build();
        productListForm.setCode(1);
        productListForm.setName("aaaa");
        productListForm.setGazou("aaaa");
        productListForm.setTmpFileName("aaaa");
        productListForm.setFile(multipartFile);

        model().attribute("productListForm",productListForm);

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
                .andExpect(model().attribute(MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_NAME,MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_URL)
                );

    }
}
