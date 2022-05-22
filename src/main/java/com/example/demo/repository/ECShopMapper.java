package com.example.demo.repository;

import com.example.demo.domain.model.MStaff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ECShopMapper
{
    public List<MStaff> staffFindMany();
    public void staffInsertOne(MStaff staff);
}
