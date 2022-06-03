package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StaffEditController {
    @Autowired
    private StaffService staffEditService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     *スタッフ一覧画面からスタッフ編集画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　PARAM_STAFF_LIST_TO_EDIT= {@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_URL}:遷移先URL
     *注意：ブラウザのURL上ではstaff/staff_listになります。
     *
     */
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT, method = RequestMethod.POST)
    public String postStaffListtoEdit(Model model,StaffListForm form) {
        System.out.println("スタッフ一覧画面からスタッフ編集画面に遷移します");
        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE);

        System.out.println(form.getRadio());
        int selectedId = Integer.valueOf(form.getRadio());
        MStaff selectedStaff = MStaff.builder().build();
        selectedStaff = staffEditService.getStaff(selectedId);

        form.setId(selectedStaff.getId());
        form.setName(selectedStaff.getName());
        form.setPassword(selectedStaff.getPassword());
        model.addAttribute(form);


        model.addAttribute("id", selectedStaff.getId());
        model.addAttribute("name", selectedStaff.getName());
        model.addAttribute("password", selectedStaff.getPassword());

        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK);

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);
        System.out.println(form);


        return MvcStatic.Staff.Edit.STAFF_EDIT_URL;

    }
    /**
     *スタッフ編集画面からスタッフ編集確認画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_CHECK_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　PARAM_STAFF_EDIT_CHECK_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_CHECK_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK, method = RequestMethod.POST)
    public String postStaffEditToCheck(Model model, @ModelAttribute @Validated StaffListForm form, BindingResult bindingResult) {
        System.out.println("staff_editからstaff_edit_checkに移動しました");
        System.out.println(form.getName());
        System.out.println(form.getId());
        System.out.println(form.getPassword());

        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_DONE_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_DONE_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE);

        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK);


        if(bindingResult.hasErrors()){
            model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_URL);
            model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK);

            model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);
            model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);
            return MvcStatic.Staff.Edit.STAFF_EDIT_URL;
        }


        model.addAttribute("id", form.getId());
        model.addAttribute("name", form.getName());
        model.addAttribute("password", form.getPassword());

        return MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL;
    }
    /**
     *スタッフ編集画面からスタッフ一覧画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　PARAM_STAFF_LIST_URL= {@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_BACK, method = RequestMethod.POST)
    public String postStaffEditBack(Model model,StaffListForm form)
    {
        System.out.println("スタッフ編集画面からスタッフリストに戻ります。");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        System.out.println("zzzz");

        List<MStaff> staffList = staffEditService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);
        return MvcStatic.Staff.STAFF_LIST_URL;

    }
    /**
     *スタッフ編集確認画面からスタッフ編集完了画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_CHECK_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_DONE_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
 * @return　PARAM_STAFF_EDIT_DONE_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_DONE_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Staff.Edit.STAFF_EDIT_DONE_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE, method = RequestMethod.POST)
    public String postStaffEditCheckToDone(Model model, StaffListForm form)
    {
        System.out.println("スタッフ編集確認画面からスタッフ編集完了画面へ遷移します");
        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_DONE_BACK,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_DONE_BACK);
        System.out.println(form.getName());

        int intId = Integer.valueOf(form.getId());
        MStaff staff = MStaff.builder()
                        .id(intId)
                        .name(form.getName())
                        .password(form.getPassword())
                        .build();
        System.out.println(staff);
        staffEditService.updateStaffone(intId,form.getName(), form.getPassword());

        return MvcStatic.Staff.Edit.STAFF_EDIT_DONE_URL;
    }

    /**
     *スタッフ編集確認画面からスタッフ編集画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_CHECK_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　PARAM_STAFF_EDIT_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Staff.Edit.STAFF_EDIT_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK, method = RequestMethod.POST)
    public String postStaffEditCheckBack(Model model, StaffListForm form)
    {
        System.out.println("スタッフ編集確認画面からスタッフ編集画面に戻ります");
        //TODO フォームの値が入っていない。
//        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL);
//        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE);
//
//        System.out.println(form.getRadio());
//        int selectedId = Integer.valueOf(form.getRadio());
//        MStaff selectedStaff = MStaff.builder().build();
//        selectedStaff = staffEditService.getStaff(selectedId);
//
//        form.setId(selectedStaff.getId());
//        form.setName(selectedStaff.getName());
//        form.setPassword(selectedStaff.getPassword());
//        model.addAttribute(form);
//
//
//        model.addAttribute("id", selectedStaff.getId());
//        model.addAttribute("name", selectedStaff.getName());
//        model.addAttribute("password", selectedStaff.getPassword());

        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK);

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);
        return MvcStatic.Staff.Edit.STAFF_EDIT_URL;
    }

    /**
     *スタッフ編集完了画面からスタッフ一覧画面に戻るコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Edit#STAFF_EDIT_DONE_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　PARAM_STAFF_LIST_URL= {@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_DONE_BACK, method = RequestMethod.POST)
    public String postStaffEditDoneBack(Model model, StaffListForm form)
    {
        System.out.println("タスク編集完了画面からスタッフリスト画面に戻ります。");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        System.out.println("zzzz");

        List<MStaff> staffList = staffEditService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);
        return MvcStatic.Staff.STAFF_LIST_URL;
    }
}