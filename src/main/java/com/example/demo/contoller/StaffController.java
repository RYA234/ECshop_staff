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

@Controller

public class StaffController
{

    @Autowired
    private StaffService staffService;
    @Autowired
   private ModelMapper modelMapper;

    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_URL,params = MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD, method = RequestMethod.POST)
    public String postStaffListToAdd(Model model)
    {
        System.out.println("bbbv");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_CHECK_NAME,MvcStatic.Staff.Add.STAFF_ADD_CHECK_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK,MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK);

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_ADD_BACK,MvcStatic.Staff.Add.PARAM_STAFF_ADD_BACK);

        return MvcStatic.Staff.Add.STAFF_ADD_URL;
    }


    //@RequestMapping(value = STAFF_LIST_URL, params = PARAM_INDEX_TO_STAFF_LIST, method = RequestMethod.POST)

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

        List<MStaff> staffList = staffService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);

        return MvcStatic.Staff.STAFF_LIST_URL;
    }
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

    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Add.PARAM_STAFF_ADD_BACK, method = RequestMethod.POST)
    public String postStaffAddBack(Model model)
    {
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        System.out.println("zzzz");

        List<MStaff> staffList = staffService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);
        return MvcStatic.Staff.STAFF_LIST_URL;
    }

    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_URL, params = MvcStatic.Staff.Add.PARAM_STAFF_CHECK_BACK, method = RequestMethod.POST)
    public String postStaffCheckBack(Model model) {
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_CHECK_NAME, MvcStatic.Staff.Add.STAFF_ADD_CHECK_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK, MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK);

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_ADD_BACK, MvcStatic.Staff.Add.PARAM_STAFF_ADD_BACK);

        return MvcStatic.Staff.Add.STAFF_ADD_URL;
    }

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
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_DONE_BACK, MvcStatic.Staff.Add.PARAM_STAFF_DONE_BACK);
        return MvcStatic.Staff.Add.STAFF_ADD_DONE_URL;
    }

    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL,params = MvcStatic.Staff.Add.PARAM_STAFF_DONE_BACK,method = RequestMethod.POST)
    public String postStaffDoneToList(Model model)
    {
        System.out.println("タスク追加画面からリストに戻ります。");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);


        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT);

        model.addAttribute(MvcStatic.Staff.Delete.STAFF_DELETE_NAME,MvcStatic.Staff.Delete.STAFF_DELETE_URL);
        model.addAttribute(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE);
        System.out.println("zzzz");

        List<MStaff> staffList = staffService.getStaffs();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);
        return MvcStatic.Staff.STAFF_LIST_URL;
    }




}
