package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.repository.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffEditController {
    @Autowired
    private StaffService staffEditService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT, method = RequestMethod.POST)
    public String postStaffListtoEdit(Model model, StaffListForm form) {
        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE);

        System.out.println(form.getRadio());
        int selectedId = Integer.valueOf(form.getRadio());
        MStaff selectedStaff = MStaff.builder().build();
        selectedStaff = staffEditService.getStaff(selectedId);

        model.addAttribute("id", selectedStaff.getId());
        model.addAttribute("name", selectedStaff.getName());
        model.addAttribute("password", selectedStaff.getPassword());

        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK);


        System.out.println();


        return MvcStatic.Staff.Edit.STAFF_EDIT_URL;

    }

    @RequestMapping(value = MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL, params = MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK, method = RequestMethod.POST)
    public String postStaffEditToCheck(Model model, StaffListForm form) {
        System.out.println("staff_editからstaff_edit_checkに移動しました");
        System.out.println(form.getName());
        System.out.println(form.getId());
        System.out.println(form.getPassword());


        model.addAttribute("id", form.getId());
        model.addAttribute("name", form.getName());
        model.addAttribute("password", form.getPassword());
        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_DONE_NAME, MvcStatic.Staff.Edit.STAFF_EDIT_DONE_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE);
        return MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL;
    }

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
}