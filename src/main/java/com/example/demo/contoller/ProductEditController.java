package com.example.demo.contoller;

import com.example.demo.form.StaffListForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductEditController {

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
     * @return　PRODUCT_EDIT_URL= {@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_URL}:遷移先URL
     *テストコード記入済み
     *
     */
    @RequestMapping(value = MvcStatic.Product.PRODUCT_LIST_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_LIST_TO_EDIT, method = RequestMethod.POST)
    public String postProductListToEdit(Model model, @ModelAttribute StaffListForm form){

        System.out.println("商品一覧画面から商品編集画面に遷移します。");
        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_NAME,MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK);
        //model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_CHECK_TO_DONE,MvcStatic.Product.Edit.PARAM_PRODUCT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);

//        model.addAttribute();
//        model.addAttribute();
//        model.addAttribute();
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
     * @return　PRODUCT_EDIT_CHECK_URL= {@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_CHECK_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK, method = RequestMethod.POST)
    public String postProductEditToCheck(Model model, @ModelAttribute StaffListForm form){

        System.out.println("商品編集画面から商品編集確認画面に遷移します。");
        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_NAME, MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE,MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_NAME, MvcStatic.Product.Edit.PRODUCT_EDIT_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK, MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK);

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
     * @return　PRODUCT_URL= {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Edit.PRODUCT_EDIT_DONE_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_TO_DONE, method = RequestMethod.POST)
    public String postProductCheckToDone(Model model, @ModelAttribute StaffListForm form){

        System.out.println("商品編集確認画面から商品編集完了画面に遷移します。");
        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME, MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST, MvcStatic.Product.PARAM_PRODUCT_LIST);

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
     * @return　PRODUCT_EDIT_URL= {@value com.example.demo.contoller.MvcStatic.Product.Edit#PRODUCT_EDIT_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.Edit.PRODUCT_EDIT_URL, params = MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_CHECK_BACK, method = RequestMethod.POST)
    public String postProductEditCheckBack(Model model, @ModelAttribute StaffListForm form){

        System.out.println("商品編集確認画面から商品編集画面に戻ります。");
        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_NAME,MvcStatic.Product.Edit.PRODUCT_EDIT_CHECK_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK,MvcStatic.Product.Edit.PARAM_PRODUCT_EDIT_TO_CHECK);

        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);

        return MvcStatic.Product.Edit.PRODUCT_EDIT_URL;
    }


}
