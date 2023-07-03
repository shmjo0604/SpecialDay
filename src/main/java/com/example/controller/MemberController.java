package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.ApplyView;
import com.example.dto.ClassImage;
import com.example.dto.ClassProduct;
import com.example.dto.Member;
import com.example.entity.ClassAnswer;
import com.example.entity.ClassInquiryViewVo;
import com.example.entity.ReviewView;
import com.example.service.apply.ApplyService;
import com.example.service.classproduct.ClassAnswerService;
import com.example.service.classproduct.ClassInquiryService;
import com.example.service.classproduct.ClassManageService;
import com.example.service.classproduct.ClassSelectService;
import com.example.service.member.MemberService;
import com.example.service.review.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    final String format = "MemberController => {}";

    @Autowired MemberService mService;
    @Autowired ClassManageService cService; 
    @Autowired ClassSelectService c1Service;
    @Autowired ApplyService aService;
    @Autowired ClassManageService manageService;
    @Autowired ReviewService r1Service;
    @Autowired ClassAnswerService answerService;
    @Autowired ClassInquiryService inquiryService;

    @Autowired ResourceLoader resourceLoader;
    @Value("${default.image}") String defaultImg;
    @Value("${myclassinquiry.page}") int inquirypagetotal;
    @Value("${mypageapply.page}") int applypagetotal;
    @Value("${mypageinquiry.page}") int myinquirypagetotal;

    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    @GetMapping(value = "/join.do")
    public String joinGET(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);
        
        return "/member/join";

    }

    @PostMapping(value = "/join.do")
    public String joinPOST(@ModelAttribute Member obj, HttpSession httpSession) {

        //log.info(format, obj.getPassword());

        obj.setPassword(bcpe.encode(obj.getPassword()));

        int ret = mService.insertMemberOne(obj);

        //log.info(format, ret);

        if(ret == 1) {
            return "redirect:joinsuccess.do";
        }
        else {
            return "redirect:/member/join.do";
        }

    }

    @GetMapping(value = "/joinsuccess.do")
    public String joinsuccessGET() {

        return "/member/joinsuccess";
    }

    @GetMapping(value = "/mypage.do")
    public String mypageGET(
            @RequestParam(name = "menu", defaultValue = "0") int menu,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "no", defaultValue = "0") int no,
            @RequestParam(name = "chk", defaultValue = "0", required = false) int chk,
            @RequestParam(name = "date", defaultValue = "0", required = false) int date,
            @AuthenticationPrincipal User user,
            Model model) {

        // log.info(format, "chk=" + chk);

        Map<String, Object> map = new HashMap<String, Object>();

        String id = user.getUsername();

        long chk1 = 0;
        long chk2 = 0;
        long chk3 = 0;

        if (menu == 0) {

            return "redirect:/member/mypage.do?menu=1&page=1";
        }

        if (menu == 1) {

            List<ApplyView> list = new ArrayList<>();

            long cnt = aService.countApplyList(id);

            long pages = ((cnt-1)/applypagetotal)+1;

            int first = page * applypagetotal - (applypagetotal-1);
            int last = page * applypagetotal;

            map.put("id", user.getUsername());
            map.put("first", first);
            map.put("last", last);

            list = aService.selectApplyListById(map);

            // log.info(format, list.toString());

            chk1 = aService.countApplyListOne(id);
            chk2 = aService.countApplyListTwo(id);
            chk3 = aService.countApplyListThree(id);

            model.addAttribute("list", list);
            model.addAttribute("pages", pages);

            model.addAttribute("chk1", chk1);
            model.addAttribute("chk2", chk2);
            model.addAttribute("chk3", chk3);

            if (chk > 3) {
                return "redirect:mypage.do?menu=1&page=1";
            }
            if (chk == 1) {
                
                list = aService.selectApplyListByIdOne(map);

                cnt = aService.countApplyListOne(id);
                pages = ((cnt-1)/applypagetotal)+1;

                model.addAttribute("list", list);
                model.addAttribute("pages", pages);

                // log.info(format, "list=" + list);

            }
            if (chk == 2) {

                list = aService.selectApplyListByIdTwo(map);

                cnt = aService.countApplyListTwo(id);
                pages = ((cnt-1)/applypagetotal)+1;

                model.addAttribute("list", list);
                model.addAttribute("pages", pages);

                // log.info(format, "list=" + list);

            }
            if (chk == 3) {

                list = aService.selectApplyListByIdThree(map);

                cnt = aService.countApplyListThree(id);
                pages = ((cnt-1)/applypagetotal)+1;

                model.addAttribute("list", list);
                model.addAttribute("pages", pages);

                // log.info(format, "list=" + list);

            }
           
        }

        else if (menu == 2) {

            List<ReviewView> list1 = new ArrayList<>();

            list1 = r1Service.selectReviewByIdPagenation(id, (page * 5) - 4, page * 5);
            if (date == 1) {
                list1 = r1Service.selectReviewByIdPagenation(id, (page * 5) - 4, page * 5);
            }

            if (date == 2) {
                list1 = r1Service.selectReviewByIdPagenationAsc(id, (page * 5) - 4, page * 5);

            }
            int total = r1Service.countReview(id);
            float avg = r1Service.avgReview(id);

            log.info(format, avg);
            log.info(format, total);
            log.info(format, list1);

            model.addAttribute("total", total);
            model.addAttribute("avg", avg);
            model.addAttribute("list1", list1);
            model.addAttribute("pages1", (total - 1) / 5 + 1); // 페이지 수

        }

        else if (menu == 3) {

            if(page == 0) {
                return "redirect:/member/mypage.do?menu=3&page=1";
            }

            long count = inquiryService.selectClassInquiryCountByMemberid(id);

            long count_chk0 = inquiryService.selectClassInquiryCountByidAndChk(id, 0);
            long count_chk1 = inquiryService.selectClassInquiryCountByidAndChk(id, 1);

            long pages = ((count-1)/myinquirypagetotal) + 1;

            int first = page * myinquirypagetotal - (myinquirypagetotal-1);
            int last = page * myinquirypagetotal;

            List<ClassInquiryViewVo> list = inquiryService.selectClassInquiryListByMemberid(id, first, last);

            if(chk == 1) {

                pages = ((count_chk0)/myinquirypagetotal) + 1;

                list = inquiryService.selectByMemberidAndChk(id, 0, first, last);

            }

            else if(chk == 2) {

                pages = ((count_chk1)/myinquirypagetotal) + 1;

                list = inquiryService.selectByMemberidAndChk(id, 1, first, last);
            }

            // log.info(format, count);
            // log.info(format, count_chk0);
            // log.info(format, count_chk1);
            // log.info(format, list.toString());

            model.addAttribute("pages", pages);
            model.addAttribute("list", list);
            model.addAttribute("count", count);
            model.addAttribute("count0", count_chk0);
            model.addAttribute("count1", count_chk1);

        }

        else if (menu == 4) {
            Member obj = mService.selectMemberOne(id);
            // slog.info(format, obj.toString());
            model.addAttribute("obj", obj);
        }

        model.addAttribute("user", user);

        return "/member/mypage/mypage";
    }

    @PostMapping(value = "/mypage.do")
    public String mypagePOST(
        @RequestParam(name = "menu", defaultValue = "0", required = false) int menu
    ) {

        return "redirect:/mypage.do?menu="+menu;
        
    }


    @GetMapping(value = "/myclass.do")
    public String myclassGET(
            @RequestParam(name = "menu", defaultValue = "0") int menu,
            @RequestParam(name = "no", defaultValue = "0", required = false) long no,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "chk", defaultValue = "-1", required = false) int chk,
            @AuthenticationPrincipal User user,
            Model model) {

        log.info(format, "chk=" + chk);

        String owner = user.getUsername();
        String id = user.getUsername();

        if (menu == 0) {
            // return "member/myclass_menu1";
            return "redirect:/member/myclass.do?menu=1";
        }

        if (menu == 2 && page == 0) {
            return "redirect:/member/myclass.do?menu=2&page=1";
        }

        if (menu == 1) {

            List<ClassProduct> list = cService.selectMyClassList(id);
            model.addAttribute("list", list);

            // log.info("myclass selectlist => {}", list.toString());
        }

        else if (menu == 2) {

            if(chk == -1){

                return "redirect:myclass.do?menu=2&page=1&chk=2";
            }

            long total = cService.selectClassInquiryListCount(owner);

            // log.info(format, total);

            long pages = ((total-1)/inquirypagetotal)+1;

            int first = (page*inquirypagetotal)-(inquirypagetotal-1);
            int last = page*inquirypagetotal; 

            List<ClassInquiryViewVo> list = cService.selectClassInquiryList(owner, first, last);
            if( chk != 2 ){
                total = cService.countByOwnerAndChk(owner, chk);
                pages = ((total-1)/inquirypagetotal)+1;
                list = cService.selectByOwnerANDChkOrderByNoDescPaging(owner, first, last, chk);
                log.info(format, list);
            }

            model.addAttribute("list", list);
            model.addAttribute("pages", pages);

            // log.info("myclass inquiry selectlist => {}", list.get(0).getRnum());

        }

        model.addAttribute("user", user);
        return "/member/myclass";
    }

    @PostMapping(value = "/myclass.do")
    public String myclassPOST(@AuthenticationPrincipal User user,
            @RequestParam(name = "classcode", defaultValue = "0", required = false) long classcode,
            @RequestParam(name = "chk", defaultValue = "-1", required = false) int chk,
            @RequestParam(name = "menu", defaultValue = "0", required = false) int menu,
            @ModelAttribute ClassProduct obj,
            @ModelAttribute ClassAnswer classAnswer,
            HttpSession httpSession,
            Model model) {

        //log.info(format, chk);
        log.info(format, classcode);

        if (menu == 1) {
            if (chk == 0 || chk == 1) {
                int ret = cService.updateClassNonactive(obj);

                //log.info(" nonactive => {}", obj.toString());
                if (ret == 1) {
                    return "redirect:/member/myclass.do?menu=1";
                }

            } else if (chk == 3) {
                int ret =  cService.updateClassActive(obj);
                
                //log.info(" active => {}", obj.toString());
                //log.info(format, ret);
                if (ret == 1) {
                    return "redirect:/member/myclass.do?menu=1";
                }
            }

        }

        else if(menu == 2) {

            // log.info(format, classAnswer.toString());

            // 1. 문의 번호로 답변 존재 유무 확인

            int count = answerService.selectClassAnswerCount(classAnswer.getNo());

            log.info(format, count);

            // 2. 서비스 호출 -> 답변 등록 or 답변 수정

            if(count == 0) {
                
                int ret = answerService.insertClassAnswerOne(classAnswer);

                // 3. 결과(ret)에 따라 페이지 이동 -> ret = 1 일 경우, /alert.do (alerturl, alertmessage session 저장)

                if(ret == 1) {

                    httpSession.setAttribute("alertMessage", "답변이 정상적으로 등록됐습니다.");
                    httpSession.setAttribute("alertUrl", "/member/myclass.do?menu="+menu);

                    return "redirect:/alert.do";
                }
            }

            else {

                int ret = answerService.updateClassAnswerOne(classAnswer);

                // 3. 결과(ret)에 따라 페이지 이동 -> ret = 1 일 경우, /alert.do (alerturl, alertmessage session 저장)

                if(ret == 1) {

                    httpSession.setAttribute("alertMessage", "답변이 정상적으로 수정됐습니다.");
                    httpSession.setAttribute("alertUrl", "/member/myclass.do?menu="+menu);

                    return "redirect:/alert.do";

                }

            }

        }

        return "redirect:/member/myclass.do?menu=" + menu;
    }

    //이미지 뛰우기 
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image(
        @RequestParam(name = "classcode", defaultValue = "0") long classcode)
    throws IOException {
       
        // log.info(format, classcode);

        long classMainNo = manageService.selectClassMainImageNo(classcode);
        ClassImage obj = manageService.selectClassImageOne(classMainNo);
  
        HttpHeaders headers = new HttpHeaders(); //import org.springframework....
    
        if (obj != null) { // 이미지가 존재하는지 확인
            if (obj.getFilesize() > 0) {
                headers.setContentType(MediaType.parseMediaType(obj.getFiletype()));
                return new ResponseEntity<>(obj.getFiledata(), headers, HttpStatus.OK);
            }
        }
        // 이미지가 없을경우
        InputStream is = resourceLoader.getResource(defaultImg).getInputStream(); // exception발생됨
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(is.readAllBytes(),headers,HttpStatus.OK);
    }

}
