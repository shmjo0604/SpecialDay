package com.example.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.ApplyView;
import com.example.dto.ClassUnit;
import com.example.service.apply.ApplyService;
import com.example.service.classproduct.ClassUnitService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping(value = "/classunit")
public class ClassUnitController {

    final String format = "ClassUnitController => {}";    
    
    @Autowired ClassUnitService cuService;
    @Autowired ApplyService aService;

    @Value("${classunitmenu2.page}") int pagetotal;
    
    @GetMapping(value = "/myunit.do")
    public String myunitGET(
        @RequestParam(name = "menu", defaultValue = "0") int menu,
        @RequestParam(name = "classcode", defaultValue = "0") long classcode,
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @AuthenticationPrincipal User user,
        Model model) {

        Map<String,Object> map = new HashMap<String,Object>();
        
        if(menu == 0 || classcode == 0) {
            return "redirect:/member/myclass.do?menu=1";
        }

        if(menu == 1) {
            long defaultPrice = cuService.selectPriceOne(classcode);
            List<ClassUnit> list = cuService.selectUnitListToCal(classcode);
            String title = cuService.selectClassProductTitleOne(classcode);

            model.addAttribute("classcode", classcode);
            model.addAttribute("defaultPrice", defaultPrice);
            model.addAttribute("list", list);
            model.addAttribute("title",title);
        }

        else if(menu == 2){

            if(page == 0) {
                return "redirect:/classunit/myunit.do?classcode="+classcode+"&menu=2&page=1";
            }

            long count = cuService.selectUnitListCountByClasscode(classcode);
            long pages = (count-1)/pagetotal + 1;

            map.put("classcode", classcode);
            map.put("first", page*pagetotal-(pagetotal-1));
            map.put("last", page*pagetotal);

            List<ClassUnit> list = cuService.selectUnitListByClasscode(map);
            
            // log.info(format, list);
            
            model.addAttribute("pages", pages);
            model.addAttribute("classcode", classcode);
            model.addAttribute("list", list);
        }

        model.addAttribute("user", user);
        return "/classunit/unit";
    }

    @GetMapping(value = "/applymanage_detail.do")
    public String applymanageGET(
        @RequestParam(name = "unitno", defaultValue = "0") long unitno,
        @RequestParam(name = "classcode", defaultValue = "0") long classcode, 
        Model model,
        @AuthenticationPrincipal User user) {

        List<ApplyView> list = aService.selectApplyViewListByUnitno(unitno);

        model.addAttribute("classcode", classcode);
        model.addAttribute("list", list);
        model.addAttribute("user", user);

        return "/classunit/menu/menu_applymanage_detail";
    }
    
}