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
    @RequestMapping(value = "staff/staff_list", params = "paramListToReference", method = RequestMethod.POST)
    public String postStaffListtoDelete(Model model, StaffListForm form)
    {
        System.out.println("スタッフ一覧画面からスタッフ参照画面に遷移します");
        int selectedId = Integer.parseInt(form.getRadio());
        MStaff selectedStaff;
        selectedStaff = staffEditService.getStaff(selectedId);
        model.addAttribute("name", selectedStaff.getName());
        model.addAttribute("id",selectedStaff.getId());

        return "staff/staff_reference";
    }

}
