package com.example.demo.domain.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MStaff
{

    private Integer id;
    private String name;
    private String password;
}
