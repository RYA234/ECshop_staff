package com.example.demo;


import com.example.demo.contoller.ProductAddController;
import com.example.demo.contoller.ProductController;
import com.example.demo.form.ProductListForm;
import com.example.demo.service.ProductService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.StaffController;
import com.example.demo.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class ProductAddControllerTest
{
    @Autowired
    private MockMvc productMvc;
    @InjectMocks
    //  IndexController testIndexController;
    ProductAddController testProductAddController;
    @Mock
    ProductService productService;

    @Mock

    ProductListForm productForm = new ProductListForm();
   MultipartFile File = null;
    @BeforeEach
    void setup()
    {
        System.out.println("abbbb");
        MockitoAnnotations.initMocks(this);
        this.productMvc = MockMvcBuilders.standaloneSetup(testProductAddController).build();
        //model().attribute(MvcStatic.Staff.INDEX_TO_STAFF_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        //model().attribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST);
       productForm.setCode(1);
        productForm.setPrice(200);
        productForm.setTmpFileName("tmp1234");
        productForm.setGazou("123111");
        productForm.setFile(File);

        model().attribute("productForm",productForm);
    }
    @Test
    @DisplayName("商品一覧画面から商品追加画面formの確認と返り値の確認_")
    public void productListToAddControllerTest() throws Exception
    {
        productMvc.perform
                        (
                                MockMvcRequestBuilders.post("/product/product_list")
                                        .param("PARAM_PRODUCT_LIST_TO_ADD","PARAM_PRODUCT_LIST_TO_ADD")
                        )
                .andDo(print())
                .andExpect(forwardedUrl("product/product_add")
                );
    }
    @Test
    @DisplayName("商品一覧画面から商品追加画面へ_Model確認")
    public void productListToAddModelTest() throws Exception
    {
        productMvc.perform
                        (
                                MockMvcRequestBuilders.post("/product/product_list")
                                        .param("PARAM_PRODUCT_LIST_TO_ADD","PARAM_PRODUCT_LIST_TO_ADD")
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("PRODUCT_ADD_CHECK_NAME","product/product_add_check"))
                .andExpect(model().attribute("PARAM_PRODUCT_ADD_TO_CHECK","PARAM_PRODUCT_ADD_TO_CHECK"))

                .andExpect(model().attribute("PRODUCT_LIST_NAME","product/product_list"))
                .andExpect(model().attribute("PARAM_PRODUCT_LIST","PARAM_PRODUCT_LIST")
                );
    }

    @Test
    @DisplayName("商品追加画面から商品追加確認画面formの確認と返り値の確認_")
    public void productAddToCheckControllerTest() throws Exception
    {
        productMvc.perform
                        (
                                MockMvcRequestBuilders.post("/product/product_add_check")
                                        .param("PARAM_PRODUCT_ADD_TO_CHECK","PARAM_PRODUCT_ADD_TO_CHECK")
                        )
                .andDo(print())
                .andExpect(forwardedUrl("product/product_add_check")
                );
        //todo テストコードでエラーになる 画像入出力用のコメントを書く
    }


//    @Test
//    @DisplayName("商品一覧画面から商品追加画面へ_Model確認")
//    public void staffListToAddModelTest() throws Exception
//    {
//        productMvc.perform
//                        (
//                                MockMvcRequestBuilders.post("/product/product_list")
//                                        .param("PARAM_PRODUCT_LIST_TO_ADD","PARAM_PRODUCT_LIST_TO_ADD")
//                        )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("PRODUCT_ADD_CHECK_NAME","product/product_add_check"))
//                .andExpect(model().attribute("PARAM_PRODUCT_ADD_TO_CHECK","PARAM_PRODUCT_ADD_TO_CHECK"))
//
//                .andExpect(model().attribute("PRODUCT_LIST_NAME","product/product_list"))
//                .andExpect(model().attribute("PARAM_PRODUCT_LIST","PARAM_PRODUCT_LIST")
//                );
//    }
    
}
