package com.example.demo;

import com.example.demo.contoller.MvcStatic;
import com.example.demo.contoller.StaffDeleteController;
import com.example.demo.form.StaffListForm;
import com.example.demo.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class StaffDeleteControllerTest {
    @Autowired
    private MockMvc staffMvc;
    @Autowired
    StaffService staffService;
    @MockBean
    private StaffDeleteController testStaffController;
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
    @DisplayName("01.スタッフ一覧画面からスタッフ削除画面HTTPステータスコードと遷移先ページ確認")
    public void staffListToDeleteControllerTest() throws Exception
    {
        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_list")
                                        // .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                                        .param(MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE,MvcStatic.Staff.Delete.PARAM_STAFF_LIST_TO_DELETE)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("staff/staff_list")
                );
    }

    @Test
    @DisplayName("02.スタッフ削除画面からスタッフ削除完了画面HTTPステータスコードと遷移先ページ確認")
    public void staffDeleteToDoneControllerTest() throws Exception
    {
        staffMvc.perform
                        (
                                MockMvcRequestBuilders.post("/staff/staff_delete_done")
                                        // .param(MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD,MvcStatic.Staff.Add.PARAM_STAFF_LIST_TO_ADD)
                                        .param(MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_TO_DONE,MvcStatic.Staff.Delete.PARAM_STAFF_DELETE_TO_DONE)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("staff/staff_delete_done")
                );
    }
}
