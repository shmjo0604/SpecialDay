package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ClassUnit;
import com.example.repository.ClassUnitRepository;
import com.example.service.apply.ApplyService;
import com.example.service.classproduct.ClassUnitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/classunit")
@RequiredArgsConstructor
public class RestClassUnitController {

    final String format = "RestClassUnit => {}";
    final ClassUnitRepository cuRepository;


    @Autowired ClassUnitService cuService;
    @Autowired ApplyService aService;

    // ************************************ 일정 관리 ********************************************
    
    // 일정 등록
    @PostMapping(value = "/insert.json")
    public Map<String, Object> insertPOST(
        @RequestBody ClassUnit classunit){
        // log.info(format, classunit.toString());
        Map<String, Object> retMap = new HashMap<>();
    
        int ret = cuService.insertUnitOne(classunit);
        
        if(ret == 1) {
            retMap.put("status", 200);
        }

        else {
            retMap.put("status",-1);
        }
        
        return retMap;
    }
    

    // 일정 리스트 조회
    @GetMapping(value = "/selectunitlist.json")
    public Map<String,Object> selectunitlistGET(
        @RequestParam(name = "classcode", defaultValue = "0") long classcode){
    
        // log.info(format, classcode);
        Map<String,Object> retmap = new HashMap<>();

        List<ClassUnit> list = cuService.selectUnitList(classcode);
        
        retmap.put("list", list);
        
        return retmap;
    }

    // 한개의 일정 조회
    @GetMapping(value = "/selectunitone.json")
    public Map<String,Object> selectunitoneGET(
        @RequestParam(name = "classcode", defaultValue = "0") long classcode,
        @RequestParam(name = "no", defaultValue = "0") long no){

        // log.info(format, classcode);
        // log.info(format, no);

        ClassUnit obj = cuService.selectUnitOne(classcode, no);
        long defaultPrice = cuService.selectPriceOne(classcode);

        // log.info(format, obj);

        Map<String, Object> retmap = new HashMap<>();
        retmap.put("obj", obj);
        retmap.put("defaultPrice2",defaultPrice);
        return retmap;
    }   

    // 선택한 일정 삭제(update로 처리)
    @PutMapping(value = "/deleteone.json")
    public Map<String, Integer> deleteonePUT(
        @RequestParam(name = "classcode", defaultValue = "0") long classcode,
        @RequestParam(name = "no", defaultValue = "0") long no) {
        Map<String, Integer> retMap = new HashMap<>();

        // log.info(format, classcode);
        // log.info(format, no);

        retMap.put("status", -1);
        
        int ret = cuService.updateUnitOne(classcode, no);

        if(ret == 1) {
            retMap.put("status", 200);
        }

        return retMap;
    }

    // 일정 전체 삭제(update로 처리)
    @PutMapping(value = "/deleteall.json")
    public Map<String, Integer> deleteallPUT(@RequestParam(name = "classcode", defaultValue = "0") long classcode) {
        Map<String, Integer> retMap = new HashMap<>();
        // log.info(format, classcode);

        retMap.put("status", -1);

        int ret = cuService.updateUnitAllInactive(classcode);

        if(ret == 1) {
            retMap.put("status", 200);
        }

        return retMap;
    }

    // 선택한 일정 수정 
    @PutMapping(value = "/updateone.json")
    public Map<String, Integer> updateonePUT(
        @RequestBody ClassUnit classunit) {
        Map<String, Integer> retMap = new HashMap<>();
        
        log.info(format, classunit);

        ClassUnit obj = cuRepository.findByClassproduct_classcodeAndNo(classunit.getClassproduct().getClasscode(), classunit.getNo());
        obj.setMinimum(classunit.getMinimum());
        obj.setMaximum(classunit.getMaximum());
        obj.setRegdate(classunit.getRegdate());
        obj.setClassdate(classunit.getClassdate());
        obj.setClassday(classunit.getClassday());
        obj.setClassstart(classunit.getClassstart());
        obj.setClassend(classunit.getClassend());
        obj.setClasslevel(classunit.getClasslevel());
        obj.setAddprice(classunit.getAddprice());
        obj.setDiscount(classunit.getDiscount());
        
        log.info(format,obj.toString());

        cuRepository.save(obj);

        retMap.put("status", 200);
        
        return retMap;
    }

    // ************************************ 신청 관리 ********************************************

    // 신청 상태 수정
    @PutMapping(value = "/updatechk.json")
    public Map<String, Integer> updatechkPUT(
        @RequestParam(name = "no", defaultValue = "0") long no){
        Map<String, Integer> retMap = new HashMap<>();
        // log.info(format, no);

        retMap.put("status", -1);

        // 1. 신청 내역의 chk를 3으로 update

        int ret = aService.updateApplyChk(no);

        if(ret == 1){

            // 2. 신청 상태 테이블에 기록 추가

            int result = aService.insertApplyStatusOne(no, 3);

            if(result == 1) {
                
                retMap.put("status", 200);

            }
        }

        return retMap;
    }
}
