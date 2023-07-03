package com.example.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Administrator;
import com.example.dto.ClassProduct;
import com.example.mapper.AdministratorMapper;

@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired AdministratorMapper adMapper;

    @Override
    public int insertAdminOne(Administrator obj) {
        try {
            return adMapper.insertAdminOne(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        } 
    }

    @Override
    public Administrator selectAdminOne(String id) {
        try {
            return adMapper.selectAdminOne(id);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    @Override
    public List<ClassProduct> selectClassProductList() {
        try {
            return adMapper.selectClassProductList();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    @Override
    public int updateClassProductChk_Confirm(long classcode) {
        try {
            return adMapper.updateClassProductChk_Confirm(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        } 
    }

    @Override
    public int updateClassProductChk_Cancle(long classcode) {
        try {
            return adMapper.updateClassProductChk_Cancle(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        } 
    }


}
