package com.example.demo;


import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.StaffController;
import com.example.demo.contoller.StaffEditController;
import com.example.demo.domain.model.MStaff;
import com.example.demo.form.ProductListForm;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class StaffEditControllerModelTest {

    @Autowired
    private MockMvc staffMvc;
    @InjectMocks
    //  IndexController testIndexController;
    StaffEditController testIndexController;
    @Mock
    StaffService staffService;
    @Mock
    StaffListForm staffListForm = new StaffListForm();

    @BeforeEach
    void setup()
    {
        System.out.println("abbbb");
        MockitoAnnotations.initMocks(this);
        this.staffMvc = MockMvcBuilders.standaloneSetup(testIndexController).build();
        //model().attribute(MvcStatic.Staff.INDEX_TO_STAFF_NAME, MvcStatic.Staff.STAFF_LIST_URL);
        //model().attribute(MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST,MvcStatic.Staff.PARAM_INDEX_TO_STAFF_LIST);
        staffListForm.setId(1);
        staffListForm.setName("test");
        staffListForm.setPassword("pass");
        staffListForm.setRadio("1");
        model().attribute("staffListForm",staffListForm);
    }

    @Test
    @DisplayName("03.スタッフ編集確認画面からスタッフ編集画面へ_Model確認")
    public void staffAddToCheckModelTest() throws Exception
    {
        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_edit")
                                        .param(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("STAFF_EDIT_NAME", "staff/staff_edit"))
                .andExpect(model().attribute("PARAM_STAFF_EDIT_TO_CHECK","PARAM_STAFF_EDIT_TO_CHECK" ))
                .andExpect(model().attribute("STAFF_LIST_NAME", "staff/staff_list"))
                .andExpect(model().attribute("PARAM_STAFF_LIST", "PARAM_STAFF_LIST")
                );
    }


    @Test
    @DisplayName("02.スタッフ編集画面からスタッフ編集確認画面へ_Model確認")
    public void staffEditCheckToDoneModelTest() throws Exception
    {



        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_edit_check")
                                        .param(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("STAFF_EDIT_DONE_NAME", "staff/staff_edit_done"))
                .andExpect(model().attribute("PARAM_STAFF_EDIT_CHECK_TO_DONE","PARAM_STAFF_EDIT_CHECK_TO_DONE" ))
                .andExpect(model().attribute("STAFF_EDIT_NAME", "staff/staff_edit"))
                .andExpect(model().attribute("PARAM_STAFF_EDIT_CHECK_BACK", "PARAM_STAFF_EDIT_CHECK_BACK")
                );
    }

    @Test
    @DisplayName("03.スタッフ編集確認画面からスタッフ編集画面へ_Model確認")
    public void staffEditCheckBackModelTest() throws Exception
    {
        //        null防止のため適当な値を入力
        MStaff seletctedStaff = MStaff.builder().build();
        seletctedStaff.setPassword("aaa");
        seletctedStaff.setId(1);
        seletctedStaff.setName("aaa");

        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_edit")
                                        .param(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK)
                        )
                .andDo(print())
                .andExpect(status().isOk()
//                .andExpect(model().attribute("STAFF_EDIT_CHECK_NAME", "staff/staff_edit_check"))
//                .andExpect(model().attribute("PARAM_STAFF_EDIT_TO_CHECK", "PARAM_STAFF_EDIT_TO_CHECK"))
//                .andExpect(model().attribute("STAFF_LIST_NAME", "staff/staff_list"))
//                .andExpect(model().attribute("PARAM_STAFF_LIST", "PARAM_STAFF_LIST")
                );
    }

}
