package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaffReferenceController
{
    @Autowired
    private StaffService staffEditService;
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.Reference.PARAM_STAFF_LIST_TO_REFERENCE, method = RequestMethod.POST)
    public String postStaffListtoDelete(Model model, StaffListForm form)
    {
        System.out.println("スタッフ一覧画面からスタッフ参照画面に遷移します");

        model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);
        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME,MvcStatic.Staff.STAFF_LIST_URL);


        int selectedId = Integer.valueOf(form.getRadio());
        MStaff selectedStaff = MStaff.builder().build();
        selectedStaff = staffEditService.getStaff(selectedId);
        model.addAttribute("name", selectedStaff.getName());
        model.addAttribute("id",selectedStaff.getId());

//        model.addAttribute(MvcStatic.Staff.Delete);
//        model.addAttribute();
//        model.addAttribute();

        return MvcStatic.Staff.Reference.STAFF_REFERENCE_URL;
    }

}
