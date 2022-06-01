package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE, method = RequestMethod.POST)
    public String postStaffListtoDelete(Model model, StaffListForm form)
    {
        System.out.println("スタッフ一覧画面からスタッフ削除画面に遷移します");

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_DONE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_DONE_URL);
        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);

        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_TO_DONE,MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_TO_DONE);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_BACK,MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_BACK);
//        model.addAttribute(MvcStatic.Staff.Delete);
//        model.addAttribute();
//        model.addAttribute();

        return MvcStatic.Staff.Delete.STAFF_DELETE_URL;
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
    @RequestMapping(value = MvcStatic.Staff.Delete.STAFF_DELETE_DONE_URL, params = MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_TO_DONE, method = RequestMethod.POST)
    public String postStaffDeleteToDone(Model model, StaffListForm form)
    {
        System.out.println("スタッフ削除画面からスタッフ削除完了画面に遷移します");

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_DONE_BACK,MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_DONE_BACK);


//        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_TO_DONE,MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_TO_DONE);
//        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_BACK,MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_BACK);
//        model.addAttribute(MvcStatic.Staff.Delete);
//        model.addAttribute();
//        model.addAttribute();

        return MvcStatic.Staff.Delete.STAFF_DELETE_DONE_URL;
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
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_BACK, method = RequestMethod.POST)
    public String postStaffDeleteBack(Model model, StaffListForm form)
    {
        System.out.println("スタッフ削除画面からスタッフ一覧画面に遷移します");

        System.out.println("スタッフ編集画面からスタッフリストに戻ります。");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        System.out.println("zzzz");

        List<MStaff> staffList = staffDeleteService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);

        return MvcStatic.Staff.STAFF_LIST_URL;
    }



    /**
     *スタッフ削除完了画面からスタッフ一覧画面に遷移するコントローラーです。<br>
     *
     * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff.Delete#STAFF_DELETE_DONE_URL}	<br>
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
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_DONE_BACK, method = RequestMethod.POST)
    public String postStaffDeleteDoneBack(Model model, StaffListForm form)
    {
        System.out.println("スタッフ削除確認画面からスタッフ一覧画面に遷移します");

        System.out.println("スタッフ編集画面からスタッフリストに戻ります。");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        System.out.println("zzzz");

        List<MStaff> staffList = staffDeleteService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);

        return MvcStatic.Staff.STAFF_LIST_URL;
    }


}
