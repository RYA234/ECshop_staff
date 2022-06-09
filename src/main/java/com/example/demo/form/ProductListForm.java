package com.example.demo.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class ProductListForm
{
    private Integer code;
//    @NotEmpty(message="商品名を入力してください")
//    @Size(max = 15, message = "商品名は15字以内で入力してください")
    private String name;
    private Integer price;

    private String gazou;
    private String radio;
    private MultipartFile file;


}
