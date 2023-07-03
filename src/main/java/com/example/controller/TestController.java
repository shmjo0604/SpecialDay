package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.Administrator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/test")
@Slf4j
@RequiredArgsConstructor
public class TestController {

    @GetMapping(value = "/test.do")
    public String testGET() {

        return "test";
    }

    @GetMapping(value = "/test1.do")
    public String test1GET() {
        return "/test/selectone";
    }

    @GetMapping(value = "/applytest.do")
    public String header1GET(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);
        
        return "/apply/insert";
    }

    @GetMapping(value = "/test2.do")
    public String headerlistGET() {

        return "/test/productdetail";
    }

}
