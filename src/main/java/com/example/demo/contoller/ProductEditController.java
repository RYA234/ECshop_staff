package com.example.demo.contoller;

import com.example.demo.domain.model.MProduct;
import com.example.demo.domain.model.MStaff;
import com.example.demo.form.ProductListForm;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.ProductService;
import com.example.demo.storage.StorageService;
import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
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

    private  final StorageService storageService;

    @Autowired
    public ProductEditController(StorageService storageService) {
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
    @RequestMapping(value = MvcStatic.Product.PRODUCT_LIST_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_LIST_TO_EDIT, method = RequestMethod.POST)
    public String postProductListToEdit(Model model,ProductListForm form){

        System.out.println("商品一覧画面から商品編集画面に遷移します。");
        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_NAME,MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK);
        //model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_CHECK_TO_DONE,MvcStatic.Product.Edit.PARAM_PRODUCT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);
        System.out.println(form.getRadio());


        MProduct selectedProduct = MProduct.builder().build();
        selectedProduct = productEditService.getProduct(Integer.valueOf(form.getRadio()));

        form.setCode(selectedProduct.getCode());
        form.setName(selectedProduct.getName());
        form.setPrice(selectedProduct.getPrice());
        form.setGazou(selectedProduct.getGazou());

        String uploadURL = "http:\\\\localhost:5000\\files\\" + form.getGazou();
        model.addAttribute("file", uploadURL);
        System.out.println("エラーが発生");

        model.addAttribute(form);

        return MvcStatic.Product.Edit.PRODUCT_EDIT_URL;
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
    @RequestMapping(value = MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK, method = RequestMethod.POST)
    public String postProductEditToCheck(Model model, @ModelAttribute @Validated ProductListForm form, BindingResult bindingResult) throws IOException {

        System.out.println("商品編集画面から商品編集確認画面に遷移します。");
        System.out.println(form);
        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_NAME, MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE,MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_NAME, MvcStatic.Product.Edit.PRODUCT_EDIT_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK);
        if(bindingResult.hasErrors())
        {
            System.out.println("追加画面の入力でエラーが発生しました。");
            model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_NAME,MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL);
            model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK);
            model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
            model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);
            return MvcStatic.Product.Edit.PRODUCT_EDIT_URL;
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

//        model.addAttribute(MvcStatic.Product);

        return MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL;
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
    @RequestMapping(value = MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE, method = RequestMethod.POST)
    public String postProductCheckToDone(Model model, @ModelAttribute ProductListForm form) throws IOException {

        System.out.println("商品編集確認画面から商品編集完了画面に遷移します。");
        System.out.println(form);
        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME, MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST, MvcStatic.Product.PARAM_PRODUCT_LIST);
        Random random = new Random();
        String newPath = String.valueOf(random.nextInt(10000000)) +".png";
        Path oldPath = Paths.get("upload-dir",form.getTmpFileName());
//        File oldFile = new File(goal.toString());
        Files.move (oldPath,oldPath.resolveSibling(newPath));
        //todo ファイルの削除を実行するとエラーになる
       // Path deletePath = Paths.get("upload-dir",form.getGazou());
        //Files.delete(deletePath);
        productEditService.updateProductone(form.getCode(),
                form.getName(),
                form.getPrice(),
                newPath
        );
        return MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_URL;
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
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Edit.PRODUCT_EDIT_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK, method = RequestMethod.POST)
    public String postProductEditCheckBack(Model model, @ModelAttribute ProductListForm form){

        System.out.println("商品編集確認画面から商品編集画面に戻ります。");
        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_NAME,MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK,MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK);

        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);

        return MvcStatic.Product.Edit.PRODUCT_EDIT_URL;
    }


}
