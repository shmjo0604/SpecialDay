package com.example.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Apply;
import com.example.dto.ApplyList;
import com.example.service.apply.ApplyService;
import com.example.service.classproduct.ClassUnitService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/apply")
public class RestApplyController {

    final String format = "RestApplyController => {}";

    @Autowired ApplyService aService;
    @Autowired ClassUnitService unitService;
    
    @PostMapping(value = "/success.json")
    public Map<String, Object> applysuccessPOST(@RequestBody ApplyList applylist, @AuthenticationPrincipal User user) {

        Map<String, Object> retMap = new HashMap<>();

        //log.info(format, applylist.toString());

        int[] paymentArray = applylist.getPaymentArray();
        int[] personArray = applylist.getPersonArray();
        int[] unitnoArray = applylist.getUnitnoArray();

        List<Apply> list = new ArrayList<>();

        for(int i=0; i<paymentArray.length; i++) {

            Apply obj = new Apply();

            obj.setMemberid(user.getUsername());
            obj.setPayment(paymentArray[i]);
            obj.setPerson(personArray[i]);
            obj.setUnitno(unitnoArray[i]);
            obj.setOwnerid(unitService.selectClassUnitViewOne(unitnoArray[i]).getMemberid());
            obj.setClasscode(unitService.selectClassUnitViewOne(unitnoArray[i]).getClasscode());

            list.add(obj);
        }

        //log.info(format, list.toString());

        int ret = aService.insertApplyBatch(list);        

        //log.info(format, ret);

        retMap.put("ret", ret);

        return retMap;
    }

}
