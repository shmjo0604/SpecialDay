package com.example.controller;

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

import com.example.entity.Basket;
import com.example.entity.BasketView;
import com.example.service.basket.BasketService;
import com.example.service.classproduct.ClassManageService;
import com.example.service.classproduct.ClassUnitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/member")
@RequiredArgsConstructor

public class BasketController {

    final String format = "BasketController => {}";

    final HttpSession httpSession; // 세션객체
    @Autowired BasketService bService;
    @Autowired ClassUnitService unitService;
    @Autowired ClassManageService classManageService;
    

    // 장바구니 페이지 접속 및 리스트 출력
    // 127.0.0.1:8080/specialday/member/basket.do

    @GetMapping(value = "/basket.do")
    public String  BasketGET(Model model,
    @AuthenticationPrincipal User user) {

        //log.info(format,user.getUsername());
        
        List<BasketView> list = bService.basketList(user.getUsername());
        
        for(BasketView  obj: list ){    //반복문을 이용한다
            
            //log.info(format, obj.getNo());

            long mainImg = classManageService.selectClassMainImageNo(obj.getClasscode());

            obj.setMainImg(mainImg);
        }

            
        model.addAttribute("list", list);
        model.addAttribute("user", user);

        // log.info(format, list.toString());

        return "/member/basket";
        
    }

    @PostMapping(value = "/basket/deleteone.do")
    public String basketdeleteonePOST(@ModelAttribute Basket basket) {
       
        // log.info(format, basket.toString());

        int ret = bService.deleteBasketOne(basket);

        if(ret == 1) {
            return "redirect:/member/basket.do";
        }

        else {
            return "redirect:/member/basket.do";
        }

    }

    @PostMapping(value = "/basket/delete.do")
    public String baksetdeletePOST(@RequestParam(name = "no[]") List<Long> list) {

        log.info(format, list.toString());

        int ret = bService.deleteBakset(list);

        if(ret == 1) {
            return "redirect:/member/basket.do";
        }
        else {
            return "redirect:/member/basket.do";
        }

    }

    
}
