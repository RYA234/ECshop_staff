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

        return MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL;
    }
}