package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MProduct
{
   private int code;
    private String name;
    private int price;
    private String gazou;
}
