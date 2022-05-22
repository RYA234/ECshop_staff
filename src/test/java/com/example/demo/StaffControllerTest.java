package com.example.demo;


import com.example.demo.contoller.IndexController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
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
        //model().attribute(MvcStatic.Staff.INDEX_TO_STAFF_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        //model().attribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST);

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
    @Test
    public void indexのコントローラーModelのテスト() throws Exception
    {
        this.loginMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("STAFF_LIST_NAME", "/staff/staff_list"))
                .andExpect(model().attribute("PARAM_INDEX_TO_STAFF_LIST", "PARAM_INDEX_TO_STAFF_LIST")
                //.andExpect(model().attribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST)
                );
    }





}
