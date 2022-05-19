package com.example.demo.contoller;




public class MvcStatic
{
    // スタッフ追加画面のURL（thymeleafと連携）

    class Staff
    {
        static final String INDEX_TO_STAFF_NAME = "INDEX_TO_STAFF_NAME";
        static final String STAFF_LIST_URL = "/staff/staff_list";
        static final String PARAM_INDEX_TO_STAFF_LIST = "PARAM_INDEX_TO_STAFF_LIST";
        class Add
        {
            static final String STAFF_ADD_NAME = "STAFF_ADD_NAME";
            static final String STAFF_ADD_URL = "/staff/staff_add";
            static final String STAFF_ADD_CHECK_NAME = "STAFF_ADD_CHECK_NAME";
            static final String STAFF_ADD_CHECK_URL = "/staff/staff_add_check";
            static final String STAFF_ADD_DONE_NAME = "STAFF_ADD_DONE_NAME";
            static final String STAFF_ADD_DONE_URL = "/staff/staff_add_done";

            // スタッフ追加画面のButtonのparamの変数

            static final String PARAM_STAFF_LIST_TO_ADD = "PARAM_STAFF_LIST_TO_ADD";
            static final String PARAM_STAFF_ADD_TO_CHECK = "PARAM_STAFF_ADD_TO_CHECK";
            static final String PARAM_STAFF_ADD_BACK = "PARAM_STAFF_ADD_BACK";
            static final String PARAM_STAFF_CHECK_TO_DONE = "PARAM_STAFF_CHECK_TO_DONE";
            static final String PARAM_STAFF_CHECK_BACK = "PARAM_STAFF_CHECK_BACK";
            static final String PARAM_STAFF_DONE_BACK = "PARAM_STAFF_DONE_BACK";

        }
        class Edit
        {

        }
        class Delete
        {

        }
        class Ref
        {

        }
    }
    class Product
    {

    }
}
