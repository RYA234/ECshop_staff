package com.example.demo.contoller;

import com.example.demo.form.StaffListForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * @return　PRODUCT_ADD_URL= {@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_URL}:遷移先URL
 *テストコード記入済み
 *
 */
    @RequestMapping(value = MvcStatic.Product.PRODUCT_LIST_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_LIST_TO_ADD, method = RequestMethod.POST)
    public String postProductListToAdd(Model model, @ModelAttribute StaffListForm form){

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
     * @return　PRODUCT_ADD_CHECK_URL= {@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_CHECK_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Add.PRODUCT_ADD_CHECK_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_ADD_TO_CHECK, method = RequestMethod.POST)
    public String postProductAddToCheck(Model model, @ModelAttribute StaffListForm form){

        System.out.println("商品追加画面から商品追加確認画面に遷移します。");
        model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_DONE_NAME, MvcStatic.Product.Add.PRODUCT_ADD_DONE_URL);
        model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE,MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_NAME, MvcStatic.Product.Add.PRODUCT_ADD_URL);
        model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_BACK, MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_BACK);

//        model.addAttribute(MvcStatic.Product);

        return MvcStatic.Product.Add.PRODUCT_ADD_CHECK_URL;
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
 * @return　PRODUCT_URL= {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
 *
 *
 */
    @RequestMapping(value = MvcStatic.Product.Add.PRODUCT_ADD_DONE_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_TO_DONE, method = RequestMethod.POST)
    public String postProductCheckToDone(Model model, @ModelAttribute StaffListForm form){

        System.out.println("商品追加確認画面から商品追加完了画面に遷移します。");
        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME, MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST, MvcStatic.Product.PARAM_PRODUCT_LIST);

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
     * @return　PRODUCT_ADD_URL= {@value com.example.demo.contoller.MvcStatic.Product.Add#PRODUCT_ADD_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Add.PRODUCT_ADD_URL, params = MvcStatic.Product.Add.PARAM_PRODUCT_CHECK_BACK, method = RequestMethod.POST)
    public String postProductAddCheckBack(Model model, @ModelAttribute StaffListForm form){

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
