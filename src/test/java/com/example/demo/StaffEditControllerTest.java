package com.example.demo;


import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.StaffController;
import com.example.demo.contoller.StaffEditController;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class StaffEditControllerTest {
    @Autowired
   private  MockMvc staffMvc;
    @Autowired
    StaffService staffService;
    @MockBean
 private StaffEditController testStaffController;
  public BindingResult bindingResult = null;

    StaffListForm staffListForm = new StaffListForm();
    @BeforeEach
    void setup()
    {
        MockitoAnnotations.initMocks(this);
        this.staffMvc = MockMvcBuilders.standaloneSetup(testStaffController).build();
        staffListForm.setId(1);
        staffListForm.setName("test");
        staffListForm.setPassword("pass");
        staffListForm.setRadio("1");
        model().attribute("staffListForm",staffListForm);

    }

    @Test
    @DisplayName("01.スタッフ一覧画面からスタッフ編集画面HTTPステータスコードと遷移先ページ確認")
    public void staffListToEditControllerTest() throws Exception
    {
        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_list")
                                        // .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                                        .param(MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT,MvcStatic.Staff.Edit.PARAM_STAFF_LIST_TO_EDIT)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("staff/staff_list")
                        // todo fowardUrlがstaff/staff_editになる模様　原因：bindingResultがfalseになるため

                );
    }

    @Test
    @DisplayName("02.スタッフ編集画面からスタッフ編集確認画面HTTPステータスコードと遷移先ページ確認")
    public void staffEditToCheckControllerTest() throws Exception
    {
        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_edit_check")
                                        // .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                                        .param(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_TO_CHECK)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("staff/staff_edit_check")
                        // todo fowardUrlがstaff/staff_editになる模様　原因：bindingResultがfalseになるため

                );
    }

    @Test
    @DisplayName("03.スタッフ編集確認画面からスタッフ編集画面HTTPステータスコードと遷移先ページ確認")
    public void staffEditBackControllerTest1() throws Exception
    {
        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_edit")
                                        // .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                                        .param(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_BACK)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("staff/staff_edit")
                        // todo fowardUrlがstaff/staff_editになる模様　原因：bindingResultがfalseになるため

                );
    }

    @Test
    @DisplayName("04.スタッフ編集確認画面からスタッフ編集完了画面HTTPステータスコードと遷移先ページ確認")
    public void staffEditCheckToDoneControllerTest() throws Exception
    {
        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_edit_done")
                                        // .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                                        .param(MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE,MvcStatic.Staff.Edit.PARAM_STAFF_EDIT_CHECK_TO_DONE)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("staff/staff_edit_done")
                        // todo fowardUrlがstaff/staff_editになる模様　原因：bindingResultがfalseになるため

                );
    }




}
