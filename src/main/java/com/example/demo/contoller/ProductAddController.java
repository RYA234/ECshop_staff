package com.example.demo.contoller;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demo.domain.model.MProduct;
import com.example.demo.form.ProductListForm;
import com.example.demo.service.ProductService;
import com.example.demo.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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
    private AmazonS3 amazonS3;

    @Autowired
    private ResourceLoader resourceLoader;
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
    @RequestMapping(value ="product/product_list", params = "PARAM_PRODUCT_LIST_TO_ADD", method = RequestMethod.POST)
    public String postProductListToAdd(Model model, @ModelAttribute ProductListForm form){

        System.out.println("商品一覧画面から商品追加画面に遷移します。");


        return "/product/product_add";
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
    @RequestMapping(value = "product/product_add_check",params = "PARAM_PRODUCT_ADD_TO_CHECK", method = RequestMethod.POST)
    public String postProductAddToCheck( Model model, @ModelAttribute @Validated ProductListForm form, BindingResult bindingResult) throws IOException {

        System.out.println("商品追加画面から商品追加確認画面に遷移します。");
        if(bindingResult.hasErrors())
        {
            System.out.println("エラーが発生しました。");
            return "product/product_add";
        }

        Random random = new Random();
        Path goal = storageService.load(form.getFile().getResource().getFilename());
         storageService.store(form.getFile());
         String newPath = "tmp" + String.valueOf(random.nextInt(10000)) +".png";
         Path oldPath = Paths.get("upload-dir/"+form.getFile().getResource().getFilename());
        File oldFile = new File(goal.toString());
        Files.move(oldPath,oldPath.resolveSibling(newPath));
        form.setGazou(newPath);
        model.addAttribute(form);
        form.setGazou(newPath);
//        model.addAttribute("files", storageService.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(ProductAddController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//        Path uploadURL = Paths.get("http://localhost:5000/files/" + newPath );

//        画像のURLを渡している

        //Todo httpを変数で表示できるようにする。（デプロイ後も対応するようにする）
        String uploadURL = "http:\\\\localhost:5000\\files\\" + newPath;
        model.addAttribute("file", uploadURL);
        System.out.println("エラーが発生");

        return "product/product_add_check";
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
    @RequestMapping(value = "product/product_add_done", params = "PARAM_PRODUCT_CHECK_TO_DONE", method = RequestMethod.POST)
    public String postProductCheckToDone(Model model, @ModelAttribute ProductListForm form) throws IOException {

        System.out.println("商品追加確認画面から商品追加完了画面に遷移します。");
        System.out.println(form);
        Random random = new Random();
        String newPath = String.valueOf(random.nextInt(10000000)) +".png";
        Path oldPath = Paths.get("upload-dir",form.getGazou());
//        File oldFile = new File(goal.toString());
//        Files.move (oldPath,oldPath.resolveSibling(newPath));
        File file = new File("upload-dir/",form.getGazou());
        amazonS3.putObject("ddadas",newPath,file);
        System.out.println("商品追加確認画面から商品追加完了画面に遷移します。");
        MProduct product = MProduct.builder()
                .name(form.getName())
                .price(form.getPrice())
                .gazou(newPath)
                .build();
        productAddService.addProduct(product);

        return "product/product_add_done";
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
    @RequestMapping(value = "product/product_add", params = "PARAM_PRODUCT_CHECK_BACK", method = RequestMethod.POST)
    public String postProductAddCheckBack(Model model, @ModelAttribute ProductListForm form){

        System.out.println("商品追加確認画面から商品追加画面に戻ります。");

        return "product/product_add";
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
