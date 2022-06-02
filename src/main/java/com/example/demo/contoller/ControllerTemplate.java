// コントローラーのテンプレートファイルです。繰り返し作業が多いので短縮するために作成しました。

//package com.example.demo.contoller;
//
//import com.example.demo.form.StaffListForm;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//public class ControllerTemplate
//{
///**編集場所は記入数
// *〇〇覧画面から〇〇画面に遷移するコントローラーです。<br>
// *
// * 遷移前URL：{@value com.example.demo.contoller.MvcStatic.Staff#STAFF_LIST_URL}	<br>
// * 遷移後URL：{@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}		<br>
// *　html側のURLとform actionの変数はthymeleafを使ってJavaの変数から渡しています。
// *
// * @param model Viewに渡す変数
// * 　　　　　	<p>1.遷移先のURL<br>
// * 				     2.formアクションの値 </p>
// *
// * @return　PARAM_STAFF_LIST_TO_ADD= {@value com.example.demo.contoller.MvcStatic.Staff.Add#STAFF_ADD_URL}:遷移先URL
// *テストコード記入済み
// *
// */
//    @RequestMapping(value = MvcStatic.Staff.Add.STAFF_ADD_DONE_URL, params = MvcStatic.Staff.Add.PARAM_STAFF_CHECK_TO_DONE, method = RequestMethod.POST)
//    public String postStaffCheckToDone(Model model, @ModelAttribute StaffListForm form){
//
//        System.out.println("〇〇画面に遷移します。");
//        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
//        model.addAttribute(MvcStatic.Staff.Add.STAFF_ADD_NAME,MvcStatic.Staff.Add.STAFF_ADD_URL);
//
//
//
//        return MvcStatic.
//    }
//
//}
