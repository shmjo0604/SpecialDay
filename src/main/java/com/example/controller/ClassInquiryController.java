package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.ClassInquiry;
import com.example.service.classproduct.ClassInquiryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/classinquiry")
public class ClassInquiryController {
    
    final String format = "ClassInquiryController => {}";

    @Autowired ClassInquiryService inquiryService;

    @PostMapping(value = "/insert.do")
    public String insertPOST(@ModelAttribute ClassInquiry obj, HttpSession httpSession) {

        // log.info(format, obj.toString());

        int ret = inquiryService.insertClassInquiryOne(obj);

        if(ret == 1) {

            httpSession.setAttribute("alertMessage", "문의가 정상적으로 등록되었습니다. 답변은 마이페이지 문의 내역에서 확인할 수 있습니다.");
            httpSession.setAttribute("alertUrl", "/class/product.do?classcode="+obj.getClassproduct().getClasscode());
            return "redirect:/alert.do";

        }

        else {

            httpSession.setAttribute("alertMessage", "문의 등록에 실패했습니다. 다시 한 번 시도해주시기 바랍니다.");
            httpSession.setAttribute("alertUrl", "/class/product.do?classcode="+obj.getClassproduct().getClasscode());
            return "redirect:/alert.do";
        }

    }

}
