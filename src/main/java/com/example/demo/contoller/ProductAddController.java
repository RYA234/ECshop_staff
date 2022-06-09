package com.example.demo.contoller;

import com.example.demo.domain.model.MProduct;
import com.example.demo.form.ProductListForm;
import com.example.demo.service.ProductService;
import com.example.demo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//public class ControllerTemplate
//{
///**編集場所は記入数
// *〇〇覧画面から〇〇画面に遷移するコントローラーです。<br>
// *
// * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}	<br>
// * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}		<br>
// *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
// *
// * @param model Viewに渡す変数
// * 　　　　　	<p>1.遷移先のURL<br>
// * 				     2.formアクションの値 </p>
// *
// * @return　PARAM_STAFF_LIST_TO_ADD= {@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}:遷移先URL
// *テストコード記入済み
// *
// */
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
//
//}
@Controller
public class ProductAddController {

    @Autowired
    ProductService productAddService;

    private final StorageService storageService;

    @Autowired
    public ProductAddController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
 *商品一覧画面から商品追加画面に遷移するコントローラーです。<br>
 *
 * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}	<br>
 * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_URL}		<br>
 *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
 *
 * @param model Viewに渡す変数
 * 　　　　　	<p>1.遷移先のURL<br>
 * 				     2.formアクションの値 </p>
 *
 * @return {@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_URL}:遷移先URL
 *テストコード記入済み
 *
 */
    @RequestMapping(value = MvcStatic.Product.PRODUCT_LIST_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_LIST_TO_ADD, method = RequestMethod.POST)
    public String postProductListToAdd(Model model, @ModelAttribute ProductListForm form){

        System.out.println("商品一覧画面から商品追加画面に遷移します。");
        model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_CHECK_NAME,MvcStatic.Product.Add.PRODUCT_ADD_CHECK_URL);
        model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_ADD_TO_CHECK, MvcStatic.Product.Add.PARAM_PRODUCT_ADD_TO_CHECK);
        //model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE,MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);

//        model.addAttribute();
//        model.addAttribute();
//        model.addAttribute();
        return MvcStatic.Product.Add.PRODUCT_ADD_URL;
    }


    /**編集場所は記入数
     *商品追加画面から商品追加確認画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_CHECK_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_CHECK_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Add.PRODUCT_ADD_CHECK_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_ADD_TO_CHECK, method = RequestMethod.POST)
    public String postProductAddToCheck( Model model, @ModelAttribute @Validated ProductListForm form, BindingResult bindingResult) throws IOException {

        System.out.println("商品追加画面から商品追加確認画面に遷移します。");
        System.out.println(form);
        model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_DONE_NAME, MvcStatic.Product.Add.PRODUCT_ADD_DONE_URL);
        model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE,MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_NAME, MvcStatic.Product.Add.PRODUCT_ADD_URL);
        model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_BACK, MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_BACK);
        if(bindingResult.hasErrors())
        {
            System.out.println("エラーが発生しました。");
            model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_CHECK_NAME,MvcStatic.Product.Add.PRODUCT_ADD_CHECK_URL);
            model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_ADD_TO_CHECK, MvcStatic.Product.Add.PARAM_PRODUCT_ADD_TO_CHECK);
            model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
            model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);
            return MvcStatic.Product.Add.PRODUCT_ADD_URL;
        }

       // Resource image = form.getFile().getResource();
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getSize());
//        System.out.println();
        Random random = new Random();
//
//        String imageFilename = "temp" + Integer.valueOf(random * 100000);
//        File newFile =
        Path goal = storageService.load(form.getFile().getResource().getFilename());
         storageService.store(form.getFile());
         String newPath = "tmp" + String.valueOf(random.nextInt(10000)) +".png";
//          String newPath = "tmp.png";

          Path oldPath = Paths.get("upload-dir/"+form.getFile().getResource().getFilename());
        Path source = Paths.get("upload-dir/tmp.png");
        File oldFile = new File(goal.toString());
        Files.move(oldPath,oldPath.resolveSibling(newPath));

//        storageService.load();

//        if(oldFile.exists()){
//
//        }else{
//            System.out.println("エラーが発生");
//        }
        model.addAttribute(form);
        form.setGazou(newPath);
//        model.addAttribute("files", storageService.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(ProductAddController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//        Path uploadURL = Paths.get("http://localhost:5000/files/" + newPath );

//        画像のURLを渡している。
        String uploadURL = "http:\\\\localhost:5000\\files\\" + newPath;
        model.addAttribute("file", uploadURL);
        System.out.println("エラーが発生");

        return MvcStatic.Product.Add.PRODUCT_ADD_CHECK_URL;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    /**編集場所は記入数
 *商品追加確認画面から商品追加完了画面に遷移するコントローラーです。<br>
 *
 * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_CHECK_URL}	<br>
 * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_DONE_URL}		<br>
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
    @RequestMapping(value = MvcStatic.Product.Add.PRODUCT_ADD_DONE_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE, method = RequestMethod.POST)
    public String postProductCheckToDone(Model model, @ModelAttribute ProductListForm form) throws IOException {

        System.out.println("商品追加確認画面から商品追加完了画面に遷移します。");
        System.out.println(form);
        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME, MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST, MvcStatic.Product.PARAM_PRODUCT_LIST);
        Random random = new Random();
        String newPath = String.valueOf(random.nextInt(10000000)) +".png";
        Path oldPath = Paths.get("upload-dir",form.getGazou());
        Path source = Paths.get("upload-dir/tmp.png");
//        File oldFile = new File(goal.toString());
        Files.move (oldPath,oldPath.resolveSibling(newPath));
        System.out.println("商品追加確認画面から商品追加完了画面に遷移します。");
        MProduct product = MProduct.builder()
                .name(form.getName())
                .price(form.getPrice())
                .gazou(newPath)
                .build();
        productAddService.addProduct(product);

        return MvcStatic.Product.Add.PRODUCT_ADD_DONE_URL;
    }

    /**編集場所は記入数
     *商品追加確認画面から商品追加画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_CHECK_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return {@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Add.PRODUCT_ADD_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_BACK, method = RequestMethod.POST)
    public String postProductAddCheckBack(Model model, @ModelAttribute ProductListForm form){

        System.out.println("商品追加確認画面から商品追加画面に戻ります。");
        model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_CHECK_NAME,MvcStatic.Product.Add.PRODUCT_ADD_CHECK_URL);
        model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE,MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);

        return MvcStatic.Product.Add.PRODUCT_ADD_URL;
    }



//    /**編集場所は記入数
//     *商品〇〇画面から商品〇〇画面に遷移するコントローラーです。<br>
//     *
//     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_CHECK_URL}	<br>
//     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_DONE_URL}		<br>
//     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
//     *
//     * @param model Viewに渡す変数
//     * 　　　　　	<p>1.遷移先のURL<br>
//     * 				     2.formアクションの値 </p>
//     *
//     * @return　PRODUCT_URL= {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
//     *
//     *
//     */
//    @RequestMapping(value = MvcStatic.Product, params = MvcStatic.Product, method = RequestMethod.POST)
//    public String postProductCheckToDone(Model model, @ModelAttribute StaffListForm form){
//
//        System.out.println("〇〇画面に遷移します。");
//        model.addAttribute(MvcStatic.Product);
//        model.addAttribute(MvcStatic.Product);
//
//
//
//        return MvcStatic.
//    }


//    /**編集場所は記入数
//     *商品〇〇画面から商品〇〇画面に遷移するコントローラーです。<br>
//     *
//     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_CHECK_URL}	<br>
//     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_DONE_URL}		<br>
//     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
//     *
//     * @param model Viewに渡す変数
//     * 　　　　　	<p>1.遷移先のURL<br>
//     * 				     2.formアクションの値 </p>
//     *
//     * @return　PRODUCT_URL= {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
//     *
//     *
//     */
//    @RequestMapping(value = MvcStatic.Product, params = MvcStatic.Product, method = RequestMethod.POST)
//    public String postProductCheckToDone(Model model, @ModelAttribute StaffListForm form){
//
//        System.out.println("〇〇画面に遷移します。");
//        model.addAttribute(MvcStatic.Product);
//        model.addAttribute(MvcStatic.Product);
//
//
//
//        return MvcStatic.
//    }

}
