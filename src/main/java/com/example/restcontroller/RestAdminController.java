package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.admin.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
public class RestAdminController {

    final String format = "RestAdmin => {}";
    @Autowired AdminService adService;

    @PutMapping(value = "/updatechkToConfirm.json")
    public Map<String, Integer> updatechkToConfirmPUT(@RequestBody Map<String, Object> request) {
        Map<String, Integer> retMap = new HashMap<>();

        long classcode = Long.parseLong(request.get("classcode").toString());
        log.info(format, classcode);

        int ret = adService.updateClassProductChk_Confirm(classcode);
        if (ret == 1) {
            retMap.put("status", 200);
        } else {
            retMap.put("status", -1);
        }

        return retMap;
    }

    @PutMapping(value = "/updatechkToCancle.json")
    public Map<String, Integer> updatechkToCanclePUT(@RequestBody Map<String, Object> request) {
        Map<String, Integer> retMap = new HashMap<>();

        long classcode = Long.parseLong(request.get("classcode").toString());
        log.info(format, classcode);

        int ret = adService.updateClassProductChk_Cancle(classcode);
        if (ret == 1) {
            retMap.put("status", 200);
        } else {
            retMap.put("status", -1);
        }

        return retMap;
    }

    
}
