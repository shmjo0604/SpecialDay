package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.Apply;
import com.example.dto.ClassUnitView;
import com.example.dto.Member;
import com.example.service.apply.ApplyService;
import com.example.service.classproduct.ClassManageService;
import com.example.service.classproduct.ClassUnitService;
import com.example.service.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/apply")
@Slf4j
public class ApplyController {

    final String format = "ApplyController => {}";

    @Autowired ClassUnitService unitService;
    @Autowired ClassManageService manageService;
    @Autowired MemberService mService;
    @Autowired ApplyService aService;

    @GetMapping(value = "/insert.do")
    public String applyGET(
        Model model, 
        @AuthenticationPrincipal User user,
        @RequestParam(name = "unitno[]", required = false) List<Long> unitnoList,
        @RequestParam(name = "person[]", required = false) List<Integer> personList) {

        //log.info(format, unitnoList);
        //log.info(format, personList);

        if(unitnoList.isEmpty() || personList.isEmpty()) {
            return "redirect:/class/select.do";
        }

        List<ClassUnitView> list = new ArrayList<>();

        for(int i=0; i<unitnoList.size(); i++) {

            ClassUnitView obj = unitService.selectClassUnitViewOne(unitnoList.get(i));

            obj.setPerson(personList.get(i));
            obj.setMainImg(manageService.selectClassMainImageNo(obj.getClasscode()));
            obj.setPayment(obj.getTotalprice() * personList.get(i));

            list.add(obj);
        }
        
        //log.info(format, list.toString());
        
        // if(obj.getMaximum()-obj.getCnt() < person) {

        //     // httpsession에 message, url 저장
        //     return "redirect:/alert.do";
        // }

        if(user != null) {

            String id = user.getUsername();
            Member member = mService.selectMemberOne(id);
            model.addAttribute("member", member);
            
        }

        model.addAttribute("list", list);
        model.addAttribute("user", user);
        
        return "/apply/insert";
        
    }

    @PostMapping(value = "/cancel.do")
    public String cancelPOST(
        @AuthenticationPrincipal User user,
        @ModelAttribute Apply apply,
        HttpSession httpSession) {

        //log.info(format, apply.toString());

        int ret = aService.updateApplyCancel(apply);

        // log.info(format, ret);

        if(ret == 1) {
            httpSession.setAttribute("alertMessage", "결제가 정상적으로 취소되었습니다.");
            httpSession.setAttribute("alertUrl", "/member/mypage.do?menu="+1+"&page="+1);
        }

        else {
            httpSession.setAttribute("alertMessage", "결제 취소에 실패했습니다. 재시도 바랍니다.");
            httpSession.setAttribute("alertUrl", "/member/mypage.do?menu="+1+"&page="+1);
        }
        
        return "redirect:/alert.do";
    }

    @GetMapping(value = "/success.do")
    public String successGET(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);

        return "/apply/success";
    }

    @GetMapping(value = "/fail.do")
    public String failGET(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);

        return "/apply/fail";
    }
    
}
