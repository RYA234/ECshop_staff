package com.example.demo.repository;

import com.example.demo.domain.model.MStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ECShopMapper
{
    public List<MStaff> staffFindMany();
    public void staffInsertOne(MStaff staff);
    public MStaff staffFindOne(Integer id);
    public void staffUpdate(@Param("id") Integer id,
                            @Param("name") String name,
                            @Param("password") String password);
}
