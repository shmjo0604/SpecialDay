package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.ClassImageRepository;
import com.example.service.classproduct.ClassManageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/classmanage")
@RequiredArgsConstructor
public class RestClassManageController {
    
    final String format = "RestClassManage => {}";
    final ClassImageRepository cRepository;

    @Autowired ClassManageService cService;

    // 선택한 서브 이미지 삭제
    @DeleteMapping(value = "/deleteone.json")
    public Map<String,Object> deleteoneDELETE(
        @RequestBody Map<String, Long> no) {
        Map<String, Object> retMap = new HashMap<>();

        log.info(format, no.get("no"));
        cRepository.deleteById(no.get("no"));
        
        return retMap;
    }

}
