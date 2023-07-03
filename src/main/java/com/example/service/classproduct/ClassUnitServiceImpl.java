package com.example.service.classproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ClassUnit;
import com.example.dto.ClassUnitView;
import com.example.mapper.ClassUnitMapper;
import com.example.repository.ClassUnitRepository;

@Service
public class ClassUnitServiceImpl implements ClassUnitService {

    @Autowired ClassUnitMapper cMapper;
    @Autowired ClassUnitRepository unitRepository;

    @Override
    public int insertUnitOne(com.example.entity.ClassUnit obj) {
        try {
            unitRepository.save(obj);
            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long selectPriceOne(long classcode) {
        try {
            return cMapper.selectPriceOne(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<com.example.entity.ClassUnit> selectUnitList(long classcode) {
        try {

            List<com.example.entity.ClassUnit> list = unitRepository.findByClassproduct_classcodeOrderByClassdate(classcode);

            List<com.example.entity.ClassUnit> list1 = new ArrayList<>();

            for(com.example.entity.ClassUnit classUnit : list) {
                if(classUnit.getChk() == 1) {
                    list1.add(classUnit);
                }
            }

            return list1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public com.example.entity.ClassUnit selectUnitOne(long classcode, long no) {
        try {
            com.example.entity.ClassUnit obj = unitRepository.findByClassproduct_classcodeAndNo(classcode, no);

            return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateUnitOne(long classcode, long no) {
        try {
            com.example.entity.ClassUnit obj = unitRepository.findByClassproduct_classcodeAndNo(classcode, no);

            obj.setChk(0);

            unitRepository.save(obj);

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateUnitOneInactive(long classcode, long no) {
        try {

            com.example.entity.ClassUnit obj = unitRepository.findByClassproduct_classcodeAndNo(classcode, no);

            obj.setChk(0);

            unitRepository.save(obj);

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateUnitAllInactive(long classcode) {
        try {
            List<com.example.entity.ClassUnit> list = unitRepository.findByClassproduct_classcodeOrderByClassdate(classcode);

            for(com.example.entity.ClassUnit classUnit : list) {
                if(classUnit.getChk() == 1) {
                    classUnit.setChk(0);
                    unitRepository.save(classUnit);
                }
            }

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ClassUnitView selectClassUnitViewOne(long unitno) {
        try {
            return cMapper.selectClassUnitViewOne(unitno);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ClassUnit> selectUnitListToCal(long classcode) {
        try {
            return cMapper.selectUnitListToCal(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ClassUnit> selectUnitListByClasscode(Map<String, Object> map) {
        try {
            return cMapper.selectUnitListByClasscode(map);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long selectUnitListCountByClasscode(long classcode) {
        try {
            return cMapper.selectUnitListCountByClasscode(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String selectClassProductTitleOne(long classcode) {
        try {
            return cMapper.selectClassProductTitleOne(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectUnitViewCntCheck(String memberid) {
        try {
            return cMapper.selectUnitViewCntCheck(memberid);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}