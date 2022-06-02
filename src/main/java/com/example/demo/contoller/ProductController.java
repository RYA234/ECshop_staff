package com.example.demo.contoller;

import com.example.demo.form.StaffListForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController
{
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
     * @return　PRODUCT_LIST_URL = {@value com.example.demo.contoller.MvcStatic.Product#PRODUCT_LIST_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Product.PRODUCT_LIST_URL, params = MvcStatic.Product.PARAM_PRODUCT_LIST, method = RequestMethod.POST)
    public String postProductToList(Model model, @ModelAttribute StaffListForm form)
    {
        System.out.println("商品一覧画面に遷移します。");
        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME,MvcStatic.Product.PRODUCT_LIST_URL);

        model.addAttribute(MvcStatic.Product.Add.PRODUCT_ADD_NAME,MvcStatic.Product.Add.PRODUCT_ADD_URL);
        model.addAttribute(MvcStatic.Product.Add.PARAM_PRODUCT_LIST_TO_ADD,MvcStatic.Product.Add.PARAM_PRODUCT_LIST_TO_ADD);

        model.addAttribute(MvcStatic.Product.Edit.PRODUCT_EDIT_NAME,MvcStatic.Product.Edit.PRODUCT_EDIT_URL);
        model.addAttribute(MvcStatic.Product.Edit.PARAM_PRODUCT_LIST_TO_EDIT,MvcStatic.Product.Edit.PARAM_PRODUCT_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Product.Delete.PRODUCT_DELETE_NAME,MvcStatic.Product.Delete.PRODUCT_DELETE_URL);
        model.addAttribute(MvcStatic.Product.Delete.PARAM_PRODUCT_LIST_TO_DELETE,MvcStatic.Product.Delete.PARAM_PRODUCT_LIST_TO_DELETE);

        model.addAttribute(MvcStatic.Product.Reference.PRODUCT_REFERENCE_NAME,MvcStatic.Product.Reference.PRODUCT_REFERENCE_URL);
        model.addAttribute(MvcStatic.Product.Reference.PARAM_PRODUCT_LIST_TO_REFERENCE,MvcStatic.Product.Reference.PARAM_PRODUCT_LIST_TO_REFERENCE);

//        model.addAttribute();
//        model.addAttribute();

        return MvcStatic.Product.PRODUCT_LIST_URL;
    }



}
