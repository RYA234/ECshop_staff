package com.example.demo;


import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.StaffController;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
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
  //  IndexController testIndexController;
    StaffController testIndexController;
    @Mock
    StaffService staffService;

    StaffListForm staffListForm = new StaffListForm();

    @BeforeEach
    void setup()
    {
        System.out.println("abbbb");
        MockitoAnnotations.initMocks(this);
        this.loginMvc = MockMvcBuilders.standaloneSetup(testIndexController).build();
        //model().attribute(MvcStatic.Staff.INDEX_TO_STAFF_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        //model().attribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST);
        staffListForm.setId(1);
        staffListForm.setName("test");
        staffListForm.setPassword("pass");
        staffListForm.setRadio("1");
        model().attribute("staffListForm",staffListForm);
    }



    @Test
    @DisplayName("01.indexのコントローラーテスト")
    public void indexControllerTest() throws Exception {
       System.out.println("aaa");

         this.loginMvc.perform(get("/index"))
                 .andDo(print())
        .andExpect(status().isOk())
                 .andExpect(forwardedUrl("/index/index")
                 );

    }
    @Test
    @DisplayName("02.indexのコントローラーModelのテスト")
    public void indexModelTest() throws Exception

    {
        this.loginMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("STAFF_LIST_NAME", "/staff/staff_list"))
                .andExpect(model().attribute("PARAM_INDEX_TO_STAFF_LIST", "PARAM_INDEX_TO_STAFF_LIST")
                //.andExpect(model().attribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST)
                );
    }

    @Test
    @DisplayName("03.スタッフ一覧画面からスタッフ追加画面HTTPステータスコード確認")
    public void staffListToAddControllerTest() throws Exception
    {
        loginMvc.perform
                        (
        MockMvcRequestBuilders.post("/staff/staff_add")
                .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                )
                .andDo(print())
                .andExpect(status().isOk()
                );
    }
    @Test
    @DisplayName("04.スタッフ一覧画面からスタッフ追加画面へ_Model確認")

    public void staffListToAddModelTest() throws Exception
    {
        loginMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_list")
                                        .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("STAFF_ADD_CHECK_NAME","staff/staff_add_check"))
                .andExpect(model().attribute("PARAM_STAFF_ADD_TO_CHECK","PARAM_STAFF_ADD_TO_CHECK"))

                .andExpect(model().attribute("STAFF_LIST_NAME","staff/staff_list"))
                .andExpect(model().attribute("PARAM_STAFF_LIST","PARAM_STAFF_LIST")
                );
    }

    @Test
    @DisplayName("05.スタッフ一覧画面からスタッフ追加画面へ返り値の確認")
    public void staffListToAddReturn() throws Exception
    {
        loginMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_list")
                                        .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                        )
                .andDo(print())
                .andExpect(forwardedUrl("staff/staff_add")
                );
    }

    @Test
    @DisplayName("03.スタッフ追加画面からスタッフ追加確認画面HTTPステータスコード確認")
    public void staffAddToCheckHttpCheck() throws Exception
    {
        loginMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_add_check")
                                        .param(MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK,MvcStatic.Staff.Add.PARAM_STAFF_ADD_TO_CHECK)
                        )
                .andDo(print())
                .andExpect(status().isOk()
                );

    }

}
