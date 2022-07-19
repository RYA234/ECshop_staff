package com.example.demo;

import com.example.demo.domain.model.MStaff;
import com.example.demo.service.StaffService;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@DbUnitConfiguration(dataSetLoader = CsvDatasSetLoader.class) // DBUnitでCSVファイルを使えるよう指定。＊CsvDataSetLoaderクラスは自作します（後述）
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
        TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや＠ExpectedDatabaseなどを使えるように指定
})
@Transactional // @DatabaseSetupで投入するデータをテスト処理と同じトランザクション制御とする。（テスト後に投入データもロールバックできるように）
public class StaffDatabaseTest
{
    @Autowired
    private StaffService staffService;

    @Test
    @DisplayName("データベーススタッフが追加されるか確認")
    @DatabaseSetup("/testdata/StaffServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/StaffServiceTest/after-create-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED) // テスト実行後に１件データが追加されていること
    void staffAddCheck() throws Exception {
        MStaff newStaff = MStaff.builder()
                .id(3)
                .name("zyx")
                .password("nnn").build();

        staffService.addStaff(newStaff);
        Assertions.assertEquals(3,newStaff.getId());
        Assertions.assertEquals("zyx",newStaff.getName());
        Assertions.assertEquals("nnn",newStaff.getPassword());
    }


    @Test
    @DisplayName("データベーススタッフが更新されるか確認")
    @DatabaseSetup("/testdata/StaffServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/StaffServiceTest/after-update-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED) // テスト実行後に１件データが追加されていること
    void staffUpdate() throws Exception {

        staffService.updateStaffone(2,"oooo","iiiii");

        Assertions.assertEquals(2,2);
        Assertions.assertEquals("oooo","oooo");
        Assertions.assertEquals("iiiii","iiiii");

    }

    @Test
    @DisplayName("データベーススタッフが一件選択されるか確認")
    @DatabaseSetup("/testdata/StaffServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/StaffServiceTest/init-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED) // テスト実行後に１件データが追加されていること
    void staffSelectOne() throws Exception {
        MStaff staff = staffService.getStaff(2);
//        2,ffgsd,dfaas
        Assertions.assertEquals(2,staff.getId());
        Assertions.assertEquals("ffgsd",staff.getName());
        Assertions.assertEquals("dfaas",staff.getPassword());

    }

    @Test
    @DisplayName("データベーススタッフが全件選択されるか確認")
    @DatabaseSetup("/testdata/StaffServiceTest/init-data")
    @ExpectedDatabase(value = "/testdata/StaffServiceTest/init-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED) // テスト実行後に１件データが追加されていること
    void staffSelectAll() throws Exception {
        List<MStaff> staff = staffService.getStaffs();

        Assertions.assertEquals("abcd", staff.get(0).getName());
        Assertions.assertEquals("zzz", staff.get(0).getPassword());
        Assertions.assertEquals("ffgsd", staff.get(1).getName());
        Assertions.assertEquals("dfaas", staff.get(1).getPassword());
    }









}
