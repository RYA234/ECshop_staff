package com.example.demo.domain.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Builder
@Data
public class MStaff
{

    private Integer id;
    private String name;
    private String password;
}
