package com.example.demo.contoller;




public class MvcStatic
{
    // スタッフ追加画面のURL（thymeleafと連携）

   public class Staff
    {
        public static final String INDEX_TO_STAFF_NAME = "INDEX_TO_STAFF_NAME";
        public static final String STAFF_LIST_NAME = "STAFF_LIST_NAME";
        public static final String STAFF_LIST_URL = "/staff/staff_list";
        public static final String PARAM_INDEX_TO_STAFF_LIST = "PARAM_INDEX_TO_STAFF_LIST";
        public class Add
        {
            public static final String STAFF_ADD_NAME = "STAFF_ADD_NAME";
            public static final String STAFF_ADD_URL = "/staff/staff_add";
            public static final String STAFF_ADD_CHECK_NAME = "STAFF_ADD_CHECK_NAME";
            public static final String STAFF_ADD_CHECK_URL = "/staff/staff_add_check";
            public static final String STAFF_ADD_DONE_NAME = "STAFF_ADD_DONE_NAME";
            public static final String STAFF_ADD_DONE_URL = "/staff/staff_add_done";

            // スタッフ追加画面のButtonのparamの変数

            public static final String PARAM_STAFF_LIST_TO_ADD = "PARAM_STAFF_LIST_TO_ADD";
            public static final String PARAM_STAFF_ADD_TO_CHECK = "PARAM_STAFF_ADD_TO_CHECK";
            public static final String PARAM_STAFF_ADD_BACK = "PARAM_STAFF_ADD_BACK";
            public static final String PARAM_STAFF_CHECK_TO_DONE = "PARAM_STAFF_CHECK_TO_DONE";
            public static final String PARAM_STAFF_CHECK_BACK = "PARAM_STAFF_CHECK_BACK";
            public static final String PARAM_STAFF_DONE_BACK = "PARAM_STAFF_DONE_BACK";

        }
        public class Edit
        {
            //↓0520追加分
            public static final String STAFF_EDIT_NAME = "STAFF_EDIT_NAME";
            public static final String STAFF_EDIT_URL = "/staff/staff_edit";
            public static final String STAFF_EDIT_CHECK_NAME = "STAFF_EDIT_CHECK_NAME";
            public static final String STAFF_EDIT_CHECK_URL = "/staff/staff_edit_check";
            public static final String STAFF_EDIT_DONE_NAME = "STAFF_EDIT_DONE_NAME";
            public static final String STAFF_EDIT_DONE_URL = "/staff/staff_edit_done";

            // スタッフ編集画面のButtonのparamの変数
            public static final String PARAM_STAFF_LIST_TO_EDIT = "PARAM_STAFF_LIST_TO_EDIT";
            public static final String PARAM_STAFF_EDIT_TO_CHECK = "PARAM_STAFF_EDIT_TO_CHECK";
            public static final String PARAM_STAFF_EDIT_BACK = "PARAM_STAFF_EDIT_BACK";
            public static final String PARAM_STAFF_EDIT_CHECK_TO_DONE = "PARAM_STAFF_EDIT_CHECK_TO_DONE";
            public static final String PARAM_STAFF_EDIT_CHECK_BACK = "PARAM_STAFF_EDIT_CHECK_BACK";
            public static final String PARAM_STAFF_EDIT_DONE_BACK = "PARAM_STAFF_EDIT_DONE_BACK";
        }
        class Delete
        {
            public static final String STAFF_DELETE_NAME = "STAFF_DELETE_NAME";
            public static final String STAFF_DELETE_URL = "/staff/staff_delete";
            public static final String STAFF_DELETE_DONE_NAME = "STAFF_DELETE_NAME";
            public static final String STAFF_DELETE_DONE_URL = "/staff/staff_delete";

            public static final String PARAM_STAFF_LIST_TO_DELETE = "PARAM_STAFF_LIST_TO_DELETE";
            public static final String PARAM_STAFF_DELETE_TO_DONE = "PARAM_STAFF_DELETE_TO_DONE";
            public static final String PARAM_STAFF_DELETE_BACK = "PARAM_STAFF_DELETE_BACK";
            public static final String PARAM_STAFF_DELETE_DONE_BACK = "PARAM_STAFF_DELETE_DONE_BACK";
        }
        class Ref
        {

        }
    }
    class Product
    {

    }
}
