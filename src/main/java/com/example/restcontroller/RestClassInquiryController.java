package com.example.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ClassAnswer;
import com.example.entity.ClassInquiry;
import com.example.entity.ClassInquiryView;
import com.example.entity.Member;
import com.example.service.classproduct.ClassAnswerService;
import com.example.service.classproduct.ClassInquiryService;
import com.example.service.classproduct.ClassManageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/inquiry")
public class RestClassInquiryController {

    @Autowired ClassInquiryService inquiryService;
    @Autowired ClassManageService manageService;
    @Autowired ClassAnswerService answerService;

    final String format = "RestClassInquiryController => {}";

    @PostMapping(value = "/insertinquiry.json")
    public Map<String, Object> insertinquiryPOST(@RequestBody ClassInquiry obj, @AuthenticationPrincipal User user) {

        Map<String, Object> retMap = new HashMap<>();

        //log.info(format, obj.toString());

        Member member = new Member();
        member.setId(user.getUsername());

        obj.setMember(member);

        //log.info(format, obj.toString());

        int ret = inquiryService.insertClassInquiryOne(obj);

        log.info(format, obj);

        retMap.put("ret", ret);

        return retMap;
    }

    @GetMapping(value = "/selectinquiry.json")
    public Map<String, Object> selectinquiryGET(
        @RequestParam(name = "classcode") long classcode,
        @RequestParam(name = "page") int page) {

        log.info(format, classcode);
        log.info(format, page);

        Map<String, Object> retMap = new HashMap<>();

        List<ClassInquiry> list = inquiryService.selectClassInquiryList(classcode);

        List<com.example.dto.ClassInquiry> retlist = new ArrayList<>();

        for(ClassInquiry rsp : list) {

            com.example.dto.ClassInquiry result = new com.example.dto.ClassInquiry();
            result.setNo(rsp.getNo());
            result.setTitle(rsp.getTitle());
            result.setContent(rsp.getContent());
            result.setRegdate(rsp.getRegdate());
            result.setMemberid(rsp.getMember().getId());

            retlist.add(result);

        }

        long pages = inquiryService.selectCountClassInquiryList(classcode);

        //log.info(format, retlist.toString());
        //log.info(format, pages);

        retMap.put("list", retlist);
        retMap.put("pages", pages);

        return retMap;
    }

    @GetMapping(value = "/selectoneinquiry.json")
    public Map<String, Object> selectoneinquiryGET(
        @RequestParam(name = "no") long no
        ) {
            
        Map<String, Object> retMap = new HashMap<>();

        // 1. 문의 번호(no)를 매개로 객체 조회
            
        ClassInquiryView obj = inquiryService.selectClassInquiryViewOne(no);

        //log.info(format, obj.toString());

        retMap.put("status", -1);

        if(obj != null) {

            // 2. null이 아닐 경우, 문의 객체 전달 (답변 객체 초기값 null 전달)

            retMap.put("status", 200);
            retMap.put("obj", obj);
            retMap.put("answer", null);

            // 3. 답변이 있을 경우, 답변 객체 전달 

            ClassAnswer classAnswer = answerService.selectClassAnswerOne(no);

            if(classAnswer != null) {
                
                //log.info(format, classAnswer.toString());
                retMap.put("answer", classAnswer);
            }
            
        }

        return retMap;
    }

}
