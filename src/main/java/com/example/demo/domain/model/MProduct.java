package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class MProduct
{
    int code;
    String name;
    int price;
    String gazou;
}
