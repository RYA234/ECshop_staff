package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StaffDeleteController {

    @Autowired
    private StaffService staffDeleteService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     *スタッフ一覧画面からスタッフ削除画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Delete#STAFF_DELETE_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　STAFF_DELETE_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Delete#STAFF_DELETE_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = "staff/staff_list", params = "PARAM_STAFF_LIST_TO_DELETE", method = RequestMethod.POST)
    public String postStaffListToDelete(Model model, StaffListForm form)
    {
        System.out.println("スタッフ一覧画面からスタッフ削除画面に遷移します");
        int selectedId = Integer.valueOf(form.getRadio());
        MStaff selectedStaff = MStaff.builder().build();
        try {
            selectedStaff = staffDeleteService.getStaff(selectedId);
            model.addAttribute("name", selectedStaff.getName());
            model.addAttribute("id",selectedStaff.getId());
        }catch (BadSqlGrammarException e){
            return "error/SQLError";
        }
        catch (Exception e) {
            return "error/NotSQLError";
        }
        return "staff/staff_delete";
    }
    /**
     *スタッフ削除画面からスタッフ削除完了画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Delete#STAFF_DELETE_URL}	<br>
     * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Delete#STAFF_DELETE_DONE_URL}		<br>
     *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
     *
     * @param model Viewに渡す変数
     * 　　　　　	<p>1.遷移先のURL<br>
     * 				     2.formアクションの値 </p>
     *
     * @return　STAFF_DELETE_DONE_URL= {@value com.example.demo.contoller.MvcStatic.Staff.Delete#STAFF_DELETE_DONE_URL}:遷移先URL
     *
     *
     */
    @RequestMapping(value = "staff/staff_delete_done", params = "PARAM_STAFF_DELETE_TO_DONE", method = RequestMethod.POST)
    public String postStaffDeleteToDone(Model model, StaffListForm form)
    {
        try {
            System.out.println("スタッフ削除画面からスタッフ削除完了画面に遷移します");
            staffDeleteService.deleteStaffone(form.getId());
        }catch (BadSqlGrammarException e){
            return "error/SQLError";
        } catch (Exception e) {
            return "error/NotSQLError";
        }
        return "staff/staff_delete_done";
    }

    /**
     *スタッフ削除画面からスタッフ一覧画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Delete#STAFF_DELETE_URL}	<br>
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
    @RequestMapping(value = "staff/staff_list", params = "PARAM_STAFF_DELETE_BACK", method = RequestMethod.POST)
    public String postStaffDeleteBack(Model model, StaffListForm form)
    {
        try {
            System.out.println("スタッフ削除or完了画面からスタッフ一覧画面に遷移します");
            List<MStaff> staffList = staffDeleteService.getStaffs();
//        System.out.println(staffList);
            model.addAttribute("staffList", staffList);
        }catch (BadSqlGrammarException e){
            return "error/SQLError";
        } catch (Exception e) {
            return "error/NotSQLError";
        }
        return "staff/staff_list";
    }
}
