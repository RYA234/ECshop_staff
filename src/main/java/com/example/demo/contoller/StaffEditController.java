package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffEditController {
    @Autowired
    private StaffService staffEditService;
    @Autowired
    private ModelMapper modelMapper;

    MStaff selectedStaff = MStaff.builder().build();

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
    @RequestMapping(value = "staff/staff_list", params = "PARAM_STAFF_LIST_TO_EDIT", method = RequestMethod.POST)
    public String postStaffListtoEdit(Model model,StaffListForm form) {
        System.out.println("スタッフ一覧画面からスタッフ編集画面に遷移します");
//        System.out.println(form.getRadio());
        int selectedId = Integer.valueOf(form.getRadio());
        try {
            selectedStaff = staffEditService.getStaff(selectedId);
        }  catch (BadSqlGrammarException e){
            return "error/SQLError";
        } catch (Exception e) {
            return "error/NotSQLError";
        }

        form.setId(selectedStaff.getId());
        form.setName(selectedStaff.getName());
        form.setPassword(selectedStaff.getPassword());
        model.addAttribute(form);
        model.addAttribute("id", selectedStaff.getId());
        model.addAttribute("name", selectedStaff.getName());
        model.addAttribute("password", selectedStaff.getPassword());

        return "staff/staff_edit";
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
    @RequestMapping(value = "staff/staff_edit_check", params = "PARAM_STAFF_EDIT_TO_CHECK", method = RequestMethod.POST)
    public String postStaffEditToCheck(Model model, @ModelAttribute @Validated StaffListForm form, BindingResult bindingResult) {
        System.out.println("staff_editからstaff_edit_checkに移動しました");

        if(bindingResult.hasErrors()){
            return "staff/staff_edit";
        }
        model.addAttribute("id", form.getId());
        model.addAttribute("name", form.getName());
        model.addAttribute("password", form.getPassword());

        return "staff/staff_edit_check";
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
    @RequestMapping(value = "staff/staff_edit_done", params = "PARAM_STAFF_EDIT_CHECK_TO_DONE", method = RequestMethod.POST)
    public String postStaffEditCheckToDone(Model model, StaffListForm form)
    {
        try {
            System.out.println("スタッフ編集確認画面からスタッフ編集完了画面へ遷移します");
            System.out.println(form.getName());

            int intId = Integer.valueOf(form.getId());
            MStaff staff = MStaff.builder()
                    .id(intId)
                    .name(form.getName())
                    .password(form.getPassword())
                    .build();
            staffEditService.updateStaffone(intId, form.getName(), form.getPassword());
        }catch (BadSqlGrammarException e){
            return "error/SQLError";
        } catch (Exception e) {
            return "error/NotSQLError";
        }
        return "staff/staff_edit_done";
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
    @RequestMapping(value = "staff/staff_edit", params = "PARAM_STAFF_EDIT_CHECK_BACK", method = RequestMethod.POST)
    public String postStaffEditCheckBack(Model model,@ModelAttribute StaffListForm form)
    {
        System.out.println("スタッフ編集確認画面からスタッフ編集画面に戻ります");
        //TODO フォームの値が入っていない。
        try {
            selectedStaff = staffEditService.getStaff(form.getId());
        } catch (BadSqlGrammarException e) {
            return "error/SQLError";
        }
        catch (Exception e) {
            return "error/NotSQLError";
        }
        int Id= selectedStaff.getId();
        form.setId(Id);
        model.addAttribute(form);
        return "staff/staff_edit";
    }

}