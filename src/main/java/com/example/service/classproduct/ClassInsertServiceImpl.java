package com.example.service.classproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ActDetailCate;
import com.example.dto.ActivityCate;
import com.example.dto.CityCate;
import com.example.dto.ClassImage;
import com.example.dto.ClassProduct;
import com.example.dto.LocalCate;
import com.example.mapper.ClassInsertMapper;
import com.example.repository.ClassImageRepository;

@Service
public class ClassInsertServiceImpl implements ClassInsertService {

    @Autowired ClassInsertMapper cMapper;
    @Autowired ClassImageRepository cImageRepository;

    @Override
    public int insertClassOne(ClassProduct obj, List<com.example.entity.ClassImage> list) {
        try {
            int ret = cMapper.insertClassOne(obj);

            if(ret == 1) {

                long classcode = cMapper.selectClasscodeRecent(obj.getMemberid());

                for(com.example.entity.ClassImage img : list) {

                    com.example.entity.ClassProduct product = new com.example.entity.ClassProduct();

                    product.setClasscode(classcode);

                    img.setClassproduct(product);

                    //img.setClasscode(classcode);
                }
                
                System.out.println(list.toString());

                cImageRepository.saveAll(list);
                //int result = cMapper.insertClassImage(list);

                //System.out.println(result);

                return 1;
            }
            else {
                return 0;
            }
            
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<CityCate> selectCityCateList() {
        try {
            return cMapper.selectCityCateList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LocalCate> selectLocalCateList(int citycode) {
        try {
            return cMapper.selectLocalCateList(citycode);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ActivityCate> selectActivityCateList() {
        try {
            return cMapper.selectActivityCateList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ActDetailCate> selectActDetailCateList(int actcode) {
        try {
            return cMapper.selectActDetailCateList(actcode);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertClassImage(List<ClassImage> obj) {
        try {
            return cMapper.insertClassImage(obj);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
