package com.example.demo.service;

import com.example.demo.domain.model.MStaff;

import java.sql.SQLException;
import java.util.List;

public interface StaffService
{
    public List<MStaff> getStaffs() throws Exception;
    public void addStaff(MStaff staff) throws Exception ;

    public MStaff getStaff(Integer id) throws Exception;
    public void updateStaffone(Integer id,
                               String name,
                               String password) throws Exception;
    public void deleteStaffone(Integer id) throws Exception;
}
