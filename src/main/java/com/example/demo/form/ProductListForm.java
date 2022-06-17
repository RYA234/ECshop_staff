package com.example.demo.form;

import lombok.Data;

@Data
public class ProductListForm
{
    private Integer code;
    private String name;

    private Integer price;
    private String gazou;
    private Integer count;
    public ProductListForm(){
        this.count=1;
    }

}
