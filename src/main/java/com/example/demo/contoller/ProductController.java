package com.example.demo.contoller;

//import com.example.demo.domain.model.MProduct;
import com.amazonaws.services.s3.AmazonS3;
import com.example.demo.domain.model.MProduct;
import com.example.demo.domain.model.MStaff;
import com.example.demo.form.ProductListForm;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductController
{

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AmazonS3 amazon3;
    //    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_DONE_URL, params = MvcStatic.Staff.Add.PARAM_STAFF_CHECK_TO_DONE, method = RequestMethod.POST)
//    public String postStaffCheckToDone(Model model, @ModelAttribute StaffListForm form){
//
//        System.out.println("〇〇画面に遷移します。");
//        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
//        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
//
//
//
//        return MvcStatic.
//    }

    /**編集場所は記入数
     *〇〇覧画面から商品一覧画面に遷移するコントローラーです。<br>
     * 遷移後URL： 複数あり
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value ="product/product_list", params ="PARAM_PRODUCT_LIST", method = RequestMethod.POST)
    public String postProductToList(Model model, @ModelAttribute ProductListForm form)
    {
        System.out.println("商品一覧画面に遷移します。");
        List<MProduct> productList = productService.getProducts();
        System.out.println(productList);
        model.addAttribute("productList", productList);

        return "product/product_list";
    }

    /**
     *商品一覧画面から商品削除画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Delete#PRODUCT_DELETE_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     * @return {@value com.example.demo.contoller.MvcStatic.Product.Delete#PRODUCT_DELETE_URL}:遷移先URL
     *
     */
    @RequestMapping(value = "product/product_list", params = "PARAM_PRODUCT_LIST_TO_DELETE", method = RequestMethod.POST)
    public String postProductListtoDelete(Model model, ProductListForm form)
    {
        System.out.println("商品一覧画面から商品削除画面に遷移します");

        MProduct selectedProduct = MProduct.builder().build();
        selectedProduct = productService.getProduct(Integer.valueOf(form.getRadio()));

        form.setCode(selectedProduct.getCode());
        form.setName(selectedProduct.getName());
        form.setPrice(selectedProduct.getPrice());
        form.setGazou(selectedProduct.getGazou());

        //Todo エラー処理していないので注意
        String uploadURL = "http:\\\\localhost:5000\\files\\" + form.getGazou() ;
        model.addAttribute(form);
        model.addAttribute("file", uploadURL);

        return "product/product_delete";
    }


    /**
     *商品削除画面から商品削除完了画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Delete#PRODUCT_DELETE_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Delete#PRODUCT_DELETE_DONE_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product.Delete#PRODUCT_DELETE_DONE_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = "product/product_delete_done", params = "PARAM_PRODUCT_DELETE_TO_DONE", method = RequestMethod.POST)
    public String postProductDeleteToDone(Model model, ProductListForm form) throws IOException {
        System.out.println("商品削除画面から商品削除完了画面に遷移します");

        Path deletePath = Paths.get("upload-dir",form.getGazou());
        Files.delete(deletePath);
        productService.deleteProductone(form.getCode());
        return "product/product_delete_done";
    }

    /**
     *商品削除画面から商品一覧画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Delete#PRODUCT_DELETE_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = "product/product_list", params = "PARAM_PRODUCT_DELETE_BACK", method = RequestMethod.POST)
    public String postProductDeleteBack(Model model, ProductListForm form)
    {
        System.out.println("商品削除画面から商品一覧画面に遷移します");
        System.out.println("商品編集画面から商品リストに戻ります。");
        return "product/product_list";
    }



    /**
     *商品削除完了画面から商品一覧画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Delete#PRODUCT_DELETE_DONE_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = "product/product_list", params = "PARAM_PRODUCT_DELETE_DONE_BACK", method = RequestMethod.POST)
    public String postProductDeleteDoneBack(Model model, ProductListForm form)
    {
        System.out.println("商品削除確認画面から商品一覧画面に遷移します");

        System.out.println("商品編集画面から商品リストに戻ります。");

        return "product/product_list";
    }

    @RequestMapping(value = "product/product_list", params = "PARAM_PRODUCT_LIST_TO_REFERENCE", method = RequestMethod.POST)
    public String postProductListtoReference(Model model, ProductListForm form)
    {
        System.out.println("商品一覧画面から商品参照画面に遷移します");

        MProduct selectedProduct = MProduct.builder().build();
        selectedProduct = productService.getProduct(Integer.valueOf(form.getRadio()));

        form.setCode(selectedProduct.getCode());
        form.setName(selectedProduct.getName());
        form.setPrice(selectedProduct.getPrice());
        form.setGazou(selectedProduct.getGazou());

        //Todo エラー処理していないので注意
//        String uploadURL = "http:\\\\localhost:5000\\files\\" + form.getGazou() ;
        String uploadURL = "https://ddadas.s3.ap-northeast-1.amazonaws.com/" + form.getGazou();
        model.addAttribute(form);
        model.addAttribute("file", uploadURL);

//        model.addAttribute(MvcStatic.Product.Delete);
//        model.addAttribute();
//        model.addAttribute();

        return "product/product_reference";
    }
}
