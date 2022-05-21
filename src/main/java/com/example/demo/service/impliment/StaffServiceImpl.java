package com.example.demo.service.impliment;

import com.example.demo.domain.model.MStaff;
import com.example.demo.repository.ECShopMapper;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService
{
    @Autowired
    private ECShopMapper StaffMapper;

    @Override
    public List<MStaff> getStaffs()
    {
            System.out.println("問題なし！");
        return StaffMapper.staffFindMany();
    }
}
