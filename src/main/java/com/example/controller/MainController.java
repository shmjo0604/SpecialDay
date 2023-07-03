package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.ClassProduct;
import com.example.entity.Community;
import com.example.service.classproduct.ClassManageService;
import com.example.service.classproduct.ClassSelectService;
import com.example.service.community.CommunityService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
    
    @Autowired ClassSelectService selectService;
    @Autowired ClassManageService cService;
    @Autowired CommunityService comService;

    @GetMapping(value="/home.do")
    public String homeGET(@AuthenticationPrincipal User user,
        Model model
    ) {
        List<ClassProduct> list = selectService.selectMainHomeClassList();

        // log.info("메인 => {}", list.size());

        if(!list.isEmpty()){
            for (ClassProduct obj : list ) {

                // System.out.println(obj.getClasscode());

                long mainImg = cService.selectClassMainImageNo(obj.getClasscode());
                long profileImg = cService.selectClassProfileImageNo(obj.getClasscode());
                obj.setMainImg(mainImg);
                obj.setProfileImg(profileImg);
            }
        }

        List<Community> list1 = comService.selectOrderByNoDescMain();

        model.addAttribute("list1", list1);
        model.addAttribute("list", list);
        model.addAttribute("user", user);
        
        return "home";
    }

    @GetMapping(value="/login.do")
    public String loginGET() {

        return "login";
        
    }

    @GetMapping(value = "/logout.do")
    public String logoutGET(HttpServletRequest request,
    HttpServletResponse response) {
        
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication != null) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return "redirect:/login.do";

    }

    @GetMapping(value = "/alert.do")
    public String alertGET(HttpSession httpSession, Model model) {

        model.addAttribute("alertMessage", httpSession.getAttribute("alertMessage"));
        model.addAttribute("alertUrl", httpSession.getAttribute("alertUrl"));
        
        httpSession.removeAttribute("alertMessage");
        httpSession.removeAttribute("alertUrl");

        return "alert";
    }

    @GetMapping(value = "/403error.do")
    public String error403GET() {
        return "403";
    }

}
