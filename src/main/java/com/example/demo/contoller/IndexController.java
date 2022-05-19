package com.example.demo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

       //URL関係の静的変数
    /*
       static final String INDEX_TO_STAFF_NAME = "INDEX_TO_STAFF_NAME";
        static final String STAFF_LIST_URL = "/staff/staff_list";


        static final String STAFF_LIST_TO_ADD_NAME = "STAFF_LIST_TO_ADD_NAME";

        // スタッフ追加画面のURL（thymeleafと連携）
        static final String STAFF_ADD_NAME = "STAFF_ADD_NAME";
        static final String STAFF_ADD_URL = "/staff/staff_add";
        static final String STAFF_ADD_CHECK_NAME = "STAFF_ADD_CHECK_NAME";
        static final String STAFF_ADD_CHECK_URL = "/staff/staff_add_check";
        static final String STAFF_ADD_DONE_NAME = "STAFF_ADD_DONE_NAME";
        static final String STAFF_ADD_DONE_URL = "/staff/staff_add_done";
        // スタッフ追加画面のButtonのparamの変数
        static final String PARAM_INDEX_TO_STAFF_LIST = "PARAM_INDEX_TO_STAFF_LIST";
        static final String PARAM_STAFF_LIST_TO_ADD = "PARAM_STAFF_LIST_TO_ADD";
        static final String PARAM_STAFF_ADD_TO_CHECK = "PARAM_STAFF_ADD_TO_CHECK";
        static final String PARAM_STAFF_ADD_BACK = "PARAM_STAFF_ADD_BACK";
        static final String PARAM_STAFF_CHECK_TO_DONE = "PARAM_STAFF_CHECK_TO_DONE";
        static final String PARAM_STAFF_CHECK_BACK = "PARAM_STAFF_CHECK_BACK";
        static final String PARAM_STAFF_DONE_BACK = "PARAM_STAFF_DONE_BACK";
*/
    @GetMapping("/index")
    public String home(Model model) {
       String Hello ="aaa";
       String Hell = "bbad";
        String HELLO_NAME = "HELLO_NAME";
      String Hello_ATTRIBUTE = "hello";
        //model.addAttribute("hello", "Heldsddddsdslo Thsd!!");
        model.addAttribute(Hello, Hell);
        //model.addAttribute(INDEX_TO_STAFF_NAME,STAFF_LIST_URL);
        model.addAttribute(HELLO_NAME, Hello_ATTRIBUTE);
        //model.addAttribute(INDEX_TO_STAFF_NAME,STAFF_LIST_URL);
        model.addAttribute(MvcStatic.Staff.INDEX_TO_STAFF_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        //model.addAttribute(PARAM_INDEX_TO_STAFF_LIST,PARAM_INDEX_TO_STAFF_LIST);
        model.addAttribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST);
        return "/index";
    }
    public enum ATTRIBUTE
    {
        ID
    }
    @RequestMapping(value = MvcStatic.Staff.STAFF_LIST_URL, params = MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST, method = RequestMethod.POST)
    //@RequestMapping(value = STAFF_LIST_URL, params = PARAM_INDEX_TO_STAFF_LIST, method = RequestMethod.POST)
    public String postStaffList(Model model)
    {
        System.out.println("aaa");
        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
        model.addAttribute(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD);

        return MvcStatic.Staff.STAFF_LIST_URL;
    }



}
