package com.example.demo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testng.annotations.Test;

@Controller
public class StaffController
{
    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_URL,params = MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD, method = RequestMethod.POST)
    public String postStaffAdd()
    {
        System.out.println("bbbv");
        return MvcStatic.Staff.Add.STAFF_ADD_URL;
    }

}
