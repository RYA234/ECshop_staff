package com.example.demo.service;

import com.example.demo.domain.model.MStaff;

import java.util.List;

public interface StaffService
{
    public List<MStaff> getStaffs();
    public void addStaff(MStaff staff);
}
