package com.example.demo;


import com.example.demo.contoller.IndexController;
import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.StaffController;
import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class StaffControllerTest
{

    @Autowired
   private MockMvc loginMvc;
    @InjectMocks
    IndexController testIndexController;
    @BeforeEach
    void setup()
    {
        System.out.println("abbbb");
        MockitoAnnotations.initMocks(this);
        this.loginMvc = MockMvcBuilders.standaloneSetup(testIndexController).build();
        model().attribute(MvcStatic.Staff.INDEX_TO_STAFF_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        model().attribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST);

    }



    @Test
    public void indexのコントローラーテスト() throws Exception {
       System.out.println("aaa");

         this.loginMvc.perform(get("/index"))
                 .andDo(print())
        .andExpect(status().isOk())
                 .andExpect(forwardedUrl("/index/index")
                 );

    }

}
