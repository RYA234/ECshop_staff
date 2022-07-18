package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * スタッフ追加機能のコントローラーです。
 *
 * スタッフ追加画面： @
 *
 * スタッフ追加確認画面：
 *
 * スタッフ追加完了画面：
 *
 *
 * @author RYA234
 *
 */
@Controller

public class StaffController
{
    StaffController(){}

    @Autowired
    private StaffService staffService;
    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    /**
     *スタッフ一覧画面からスタッフ画面追加に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　PARAM_STAFF_LIST_TO_ADD= {@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}:遷移先URL
     *テストコード記入済み
     *
     */
    @RequestMapping(value = "staff/staff_list",params = "PARAM_STAFF_LIST_TO_ADD", method = RequestMethod.POST)
    public String postStaffListToAdd(Model model,StaffListForm form)
    {
        System.out.println("スタッフ一覧からスタッフ追加画面に遷移します。");
        return "staff/staff_add";
    }


    @RequestMapping(value = "staff/staff_list", params = "PARAM_INDEX_TO_STAFF_LIST", method = RequestMethod.POST)
    public String postStaffList(@ModelAttribute StaffListForm form, Model model)
    {
        List<MStaff> staffList = staffService.getStaffs();
        model.addAttribute("staffList", staffList);

        return "staff/staff_list";
    }
    /**
     *スタッフ追加画面からスタッフ追加確認画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL："/staff/staff_add"	<br>
     * 遷移後URL："/staff/staff_add_check"		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　"/staff/staff_add_check":遷移先URL
     *
     *テストコード記入済み
     */
    @RequestMapping(value = "staff/staff_add_check", params = "PARAM_STAFF_ADD_TO_CHECK", method = RequestMethod.POST)
    public String postStaffAddToCheck(Model model,@ModelAttribute @Validated StaffListForm form, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "staff/staff_add";
        }
        model.addAttribute("name",form.getName());
        model.addAttribute("password",form.getPassword());
        return "/staff/staff_add_check";
    }

    /**
     *スタッフ追加画面からスタッフ一覧画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　STAFF_LIST_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_LIST_URL}:遷移先URL
     *
     *テストコード記入済み
     */
    @RequestMapping(value = "staff/staff_list", params = "PARAM_STAFF_LIST", method = RequestMethod.POST)
    public String postStaffList(Model model)
    {
        System.out.println("スタッフ一覧画面に遷移します。");
        List<MStaff> staffList = staffService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);
        return "staff/staff_list";
    }
    /**
     *スタッフ追加確認画面からスタッフ追加画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_CHECK_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　STAFF_ADD_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}:遷移先URL
     *
     *テストコード記入済み
     */
    @RequestMapping(value = "staff/staff_add", params = "PARAM_STAFF_CHECK_BACK", method = RequestMethod.POST)
    public String postStaffCheckBack(Model model) {

        return "staff/staff_add";
    }

    /**
     *スタッフ追加確認画面からスタッフ追加完了画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_CHECK_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_DONE_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　STAFF_ADD_DONE_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_DONE_URL}:遷移先URL
     *
     *テストコード記入済み
     */
    @RequestMapping(value = "staff/staff_add_done", params = "PARAM_STAFF_CHECK_TO_DONE", method = RequestMethod.POST)
    public String postStaffCheckToDone(Model model,@ModelAttribute StaffListForm form)
    {
        System.out.println("スタッフ追加確認画面からスタッフ追加完了画面に遷移しました。");
        System.out.println(form.getName());
        System.out.println(form.getPassword());
//       String encodePassword = passwordEncoder.encode(form.getPassword());
        MStaff staff = MStaff.builder()
                .name(form.getName())
                .password(form.getPassword())
                .build();
        staffService.addStaff(staff);
        return "staff/staff_add_done";
    }





}
