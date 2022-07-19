package com.example.demo.contoller;

import com.example.demo.domain.model.MStaff;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLSyntaxErrorException;

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
        try {
            selectedStaff = staffEditService.getStaff(selectedId);
            model.addAttribute("name", selectedStaff.getName());
            model.addAttribute("id",selectedStaff.getId());
        } catch (BadSqlGrammarException e){
            return "error/SQLError";
        } catch (Exception e) {
            return "error/NotSQLError";
        }
        return "staff/staff_reference";
    }

}
