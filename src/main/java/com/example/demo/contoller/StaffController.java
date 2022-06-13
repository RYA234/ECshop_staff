package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    StaffController(){};
    @Autowired
    private StaffService staffService;
    @Autowired
    private ModelMapper modelMapper;

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
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL,params = MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD, method = RequestMethod.POST)
    public String postStaffListToAdd(Model model)
    {
        System.out.println("スタッフ一覧からスタッフ追加画面");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_CHECK_NAME,MvcStatic.Staff.Add.STAFF_ADD_CHECK_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK,MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK);

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);
        return MvcStatic.Staff.Add.STAFF_ADD_URL;
    }




    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST, method = RequestMethod.POST)
    public String postStaffList(@ModelAttribute StaffListForm form, Model model)
    {
        System.out.println("aaa");

        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        System.out.println("zzzz");
        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        List<MStaff> staffList = staffService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);

        return MvcStatic.Staff.STAFF_LIST_URL;
    }
    /**
     *スタッフ追加画面からスタッフ追加確認画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_CHECK_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　STAFF_ADD_CHECK_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_CHECK_URL}:遷移先URL
     *
     *テストコード記入済み
     */
    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_CHECK_URL, params = MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK, method = RequestMethod.POST)
    public String postStaffAddToCheck(Model model,@ModelAttribute StaffListForm form)
    {
        System.out.println("deffffffsda");
        System.out.println(form.getName());
        System.out.println(form.getPassword());
//        MStaff staff =  MStaff.builder().build();
//
//
//        System.out.println(staff.getPassword());
        model.addAttribute("name",form.getName());
        model.addAttribute("password",form.getPassword());
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_DONE_NAME,MvcStatic.Staff.Add.STAFF_ADD_DONE_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_CHECK_TO_DONE,MvcStatic.Staff.Add.PARAM_STAFF_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_CHECK_BACK,MvcStatic.Staff.Add.PARAM_STAFF_CHECK_BACK);

//        MStaff teststaff = MStaff.builder()
//                .name("zyx")
//                .password("nnn").build();

       // MStaff staff = modelMapper.map(form, MStaff.class);
        //System.out.println(staff);
        //staffService.addStaff(teststaff);

        return MvcStatic.Staff.Add.STAFF_ADD_CHECK_URL;
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
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.PARAM_STAFF_LIST, method = RequestMethod.POST)
    public String postStaffList(Model model)
    {
        System.out.println("スタッフ一覧画面に遷移します。");

        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        model.addAttribute(MvcStatic.Staff.Reference.PARAM_STAFF_LIST_TO_REFERENCE, MvcStatic.Staff.Reference.PARAM_STAFF_LIST_TO_REFERENCE );
        System.out.println("zzzz");

        List<MStaff> staffList = staffService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);
        return MvcStatic.Staff.STAFF_LIST_URL;
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
    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_URL, params = MvcStatic.Staff.Add.PARAM_STAFF_CHECK_BACK, method = RequestMethod.POST)
    public String postStaffCheckBack(Model model) {
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_CHECK_NAME, MvcStatic.Staff.Add.STAFF_ADD_CHECK_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK, MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK);

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);
        return MvcStatic.Staff.Add.STAFF_ADD_URL;
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
    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_DONE_URL, params = MvcStatic.Staff.Add.PARAM_STAFF_CHECK_TO_DONE, method = RequestMethod.POST)
    public String postStaffCheckToDone(Model model,@ModelAttribute StaffListForm form)
    {
        System.out.println("スタッフ追加確認画面からスタッフ追加完了画面に遷移しました。");
        System.out.println(form.getName());
        System.out.println(form.getPassword());
        MStaff staff = MStaff.builder()
                .name(form.getName())
                .password(form.getPassword())
                .build();
        staffService.addStaff(staff);
        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);
        return MvcStatic.Staff.Add.STAFF_ADD_DONE_URL;
    }

    /**
     *スタッフ追加完了画面からスタッフ一覧画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_DONE_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　STAFF_LIST_URL= {@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}:遷移先URL
     *
     *
     */





}
