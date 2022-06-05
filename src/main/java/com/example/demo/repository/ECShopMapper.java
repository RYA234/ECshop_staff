package com.example.demo.repository;

import com.example.demo.domain.model.MProduct;
import com.example.demo.domain.model.MStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ECShopMapper
{
    List<MStaff> staffFindMany();
    void staffInsertOne(MStaff staff);
    MStaff staffFindOne(Integer id);
    void staffUpdate(@Param("id") Integer id,
                            @Param("name") String name,
                            @Param("password") String password);

    void staffDelete(Integer id);

    List<MProduct> productFindMany();
    void productInsertOne(MProduct product);
    MProduct productFindOne(Integer code);
    void productUpdate(@Param("code") Integer code,
                            @Param("name") String name,
                            @Param("price") Integer price,
                            @Param("gazou") String gazou);

    void productDelete(Integer code);
    

}
