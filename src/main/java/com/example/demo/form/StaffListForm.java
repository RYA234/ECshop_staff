package com.example.demo.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class StaffListForm
{

    private Integer id;
    @NotEmpty(message="名前を入力してください")
    @Size(max = 15, message = "名前は15字以内で入力してください")
    private String name;

    @NotEmpty(message="パスワードを入力してください")
    @Size(max = 15, message = "パスワードは15字以内で入力してください")
    private String password;
    private String radio;
}
