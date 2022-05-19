package com.example.demo;


import com.example.demo.contoller.IndexController;
import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.StaffController;
import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;
import org.junit.Before;
import org.junit.Test;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class StaffControllerTest
{

    @Autowired
   MockMvc loginMvc;
    @BeforeEach
    void setup()
    {
        System.out.println("abbbb");


    }



    @Test
    public void テスihooihjト() throws Exception {
       System.out.println("aaa");
        MockitoAnnotations.initMocks(this);
        this.loginMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
         this.loginMvc.perform(get("/index"))
                 .andDo(print())
        .andExpect(status().isOk());

    }

}
