package com.example.demo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {


    @GetMapping("/index")
    //public String home() {
    public String home(Model model) {
       String Hello ="aaa";
       String Hell = "bbad";
       String HELLO_NAME = "HELLO_NAME";
       String Hello_ATTRIBUTE = "hello";
        //model.addAttribute(INDEX_TO_STAFF_NAME,STAFF_LIST_URL);
       // model.addAttribute(HELLO_NAME, Hello_ATTRIBUTE);

        model.addAttribute(MvcStatic.Staff.STAFF_LIST_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.PARAM_STAFF_LIST,MvcStatic.Staff.PARAM_STAFF_LIST);

        model.addAttribute(MvcStatic.Product.PRODUCT_LIST_NAME, MvcStatic.Product.PRODUCT_LIST_URL);
        model.addAttribute(MvcStatic.Product.PARAM_PRODUCT_LIST,MvcStatic.Product.PARAM_PRODUCT_LIST);

        return "/index/index";
    }
    public enum ATTRIBUTE
    {
        ID
    }





}
