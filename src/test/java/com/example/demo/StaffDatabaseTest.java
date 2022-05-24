//package com.example.demo;
//
//import com.example.demo.domain.model.MStaff;
//import com.example.demo.service.StaffService;
//import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
//import com.github.springtestdbunit.annotation.DatabaseSetup;
//import com.github.springtestdbunit.annotation.DbUnitConfiguration;
//import com.github.springtestdbunit.annotation.ExpectedDatabase;
//import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@SpringBootTest
//@DbUnitConfiguration(dataSetLoader = CsvDatasSetLoader.class) // DBUnitでCSVファイルを使えるよう指定。＊CsvDataSetLoaderクラスは自作します（後述）
//@TestExecutionListeners({
//        DependencyInjectionTestExecutionListener.class, // このテストクラスでDIを使えるように指定
//        TransactionDbUnitTestExecutionListener.class // @DatabaseSetupや＠ExpectedDatabaseなどを使えるように指定
//})
//@Transactional // @DatabaseSetupで投入するデータをテスト処理と同じトランザクション制御とする。（テスト後に投入データもロールバックできるように）
//public class StaffDatabaseTest
//{
//    @Autowired
//    private StaffService staffService;
//
//    @Test
//    @DatabaseSetup("/testdata/StaffServiceTest/init-data")
//    @ExpectedDatabase(value = "/testdata/StaffServiceTest/after-create-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED) // テスト実行後に１件データが追加されていること
//    void データベーススタッフが追加されるか確認()
//    {
//        MStaff newStaff = MStaff.builder()
//                .id(4)
//                .name("zyx")
//                .password("nnn").build();
//
//        staffService.addStaff(newStaff);
//        Assertions.assertEquals(4,newStaff.getId());
//        Assertions.assertEquals("zyx",newStaff.getName());
//        Assertions.assertEquals("nnn",newStaff.getPassword());
//    }
//
//    @Test
//    @DatabaseSetup("/testdata/StaffServiceTest/init-data")
//    @ExpectedDatabase(value = "/testdata/StaffServiceTest/after-delete-data", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED) // テスト実行後に１件データが追加されていること
//    void データベーススタッフが削除されるか確認()
//    {
//        staffService.deleteStaff(2);
//
//    }
//}
