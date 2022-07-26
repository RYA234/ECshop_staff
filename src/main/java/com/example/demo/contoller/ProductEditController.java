package com.example.demo.contoller;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demo.domain.model.MProduct;
import com.example.demo.form.ProductListForm;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;
import com.example.demo.service.storage.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Controller
public class ProductEditController {

    @Autowired
    private ProductService productEditService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AmazonS3 amazonS3;
    private  final StorageService storageService;
    private final UploadService uploadService;
    @Autowired
    public ProductEditController(UploadService uploadService, StorageService storageService) {
        this.uploadService =  uploadService;
        this.storageService = storageService;
    }




    /**
     *商品一覧画面から商品編集画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_URL}:遷移先URL
     *テストコード記入済み
     *
     */
    @RequestMapping(value = "product/product_list", params = "PARAM_PRODUCT_LIST_TO_EDIT", method = RequestMethod.POST)
    public String postProductListToEdit(Model model,ProductListForm form){

        System.out.println("商品一覧画面から商品編集画面に遷移します。");

        System.out.println(form.getRadio());


        MProduct selectedProduct = MProduct.builder().build();
        selectedProduct = productEditService.getProduct(Integer.valueOf(form.getRadio()));

        form.setCode(selectedProduct.getCode());
        form.setName(selectedProduct.getName());
        form.setPrice(selectedProduct.getPrice());
        form.setGazou(selectedProduct.getGazou());

//        String uploadURL = "http:\\\\localhost:5000\\files\\" + form.getGazou();
        String uploadURL = "https://ddadas.s3.ap-northeast-1.amazonaws.com/" + form.getGazou();
        model.addAttribute("file", uploadURL);
        System.out.println("エラーが発生");

        model.addAttribute(form);

        return "product/product_edit";
    }


    /**編集場所は記入数
     *商品編集画面から商品編集確認画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_CHECK_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_CHECK_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value ="product/product_edit_check" , params = "PARAM_PRODUCT_EDIT_TO_CHECK", method = RequestMethod.POST)
    public String postProductEditToCheck(Model model, @ModelAttribute @Validated ProductListForm form, BindingResult bindingResult) throws IOException {

        System.out.println("商品編集画面から商品編集確認画面に遷移します。");
        System.out.println(form);
        if(bindingResult.hasErrors())
        {
            System.out.println("追加画面の入力でエラーが発生しました。");
            return "product/product_edit";
        }
        Random random = new Random();
      //  Path goal = storageService.load(form.getFile().getResource().getFilename());
        storageService.store(form.getFile());
        String newPath = "tmp" + String.valueOf(random.nextInt(10000)) +".png";
        Path oldPath = Paths.get("upload-dir/"+form.getFile().getResource().getFilename());
        //File oldFile = new File(goal.toString());
        Files.move(oldPath,oldPath.resolveSibling(newPath));
        form.setTmpFileName(newPath);
        model.addAttribute(form);

        String uploadURL = "http:\\\\localhost:5000\\files\\" + newPath;
        model.addAttribute("file", uploadURL);
        return "product/product_edit_check";
    }


    /**編集場所は記入数
     *商品編集確認画面から商品編集完了画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_CHECK_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_DONE_URL}		<br>
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
    @RequestMapping(value = "product/product_edit_done", params = "PARAM_PRODUCT_EDIT_CHECK_TO_DONE", method = RequestMethod.POST)
    public String postProductCheckToDone(Model model, @ModelAttribute ProductListForm form) throws IOException {

        System.out.println("商品編集確認画面から商品編集完了画面に遷移します。");
        System.out.println(form);
        Random random = new Random();
        String newPath = String.valueOf(random.nextInt(10000000)) +".png";
        Path oldPath = Paths.get("upload-dir",form.getTmpFileName());
//        File oldFile = new File(goal.toString());
        Files.move (oldPath,oldPath.resolveSibling(newPath));
       Path deletePath = Paths.get("upload-dir",form.getGazou());
        File file = new File("upload-dir/",form.getGazou());
        amazonS3.putObject("ddadas",newPath,file);
        Files.delete(deletePath);

        productEditService.updateProductone(form.getCode(),
                form.getName(),
                form.getPrice(),
                newPath
        );
        return "product/product_edit_done";
    }

    /**編集場所は記入数
     *商品編集確認画面から商品編集画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_CHECK_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_URL}:遷移先URL
     */
    @RequestMapping(value = "product/product_edit", params = "PARAM_PRODUCT_EDIT_CHECK_BACK", method = RequestMethod.POST)
    public String postProductEditCheckBack(Model model, @ModelAttribute ProductListForm form){

        System.out.println("商品編集確認画面から商品編集画面に戻ります。");
        return "product/product_edit";
    }


}
