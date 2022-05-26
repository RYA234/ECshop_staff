package com.example.demo.contoller;

import com.example.demo.form.StaffListForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffEditController
{
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL,params = MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT, method = RequestMethod.POST)
    public String postStaffListtoEdit(Model model, StaffListForm form)
    {
        model.addAttribute(MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_NAME,MvcStatic.Staff.Edit.STAFF_EDIT_CHECK_URL);
        model.addAttribute(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE, MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE);

        System.out.println(form.getRadio());

        return MvcStatic.Staff.Edit.STAFF_EDIT_URL;

    }


}
