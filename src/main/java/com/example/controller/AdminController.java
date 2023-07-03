package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.Administrator;
import com.example.dto.ClassProduct;
import com.example.service.admin.AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
    @Autowired AdminService adService;
    final String format = "AdminController => {}";

    // 관리자 등록
    @GetMapping(value = "/insert.do")
    public String insertGET() {
        return "/admin/insert";
    }

    @PostMapping(value = "/insert.do")
    public String insertPOST(@ModelAttribute Administrator obj, HttpSession httpSession) {

        obj.setPassword(bcpe.encode(obj.getPassword()));

        int ret = adService.insertAdminOne(obj);

        if (ret == 1) {
            return "redirect:/admin/login.do";
        }
        return "redirect:/admin/insert.do";
    }

    // 관리자 로그인
    @GetMapping(value = "/login.do")
    public String loginGET() {
        return "/admin/login";
    }

    // 관리자 홈
    @GetMapping(value = "/home.do")
    public String homeGET(Model model) {

        List<ClassProduct> list = adService.selectClassProductList();
        // log.info(format, list.toString());
        model.addAttribute("list", list);

        return "/admin/home";
    }

    

}
