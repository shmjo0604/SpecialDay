package com.example.restcontroller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApplyStatusView;
import com.example.dto.ApplyView;
import com.example.dto.Authnum;
import com.example.dto.Member;
import com.example.service.MailService;
import com.example.service.RedisUtil;
import com.example.service.apply.ApplyService;
import com.example.service.classproduct.ClassUnitService;
import com.example.service.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/member")
public class RestMemberController {

    @Autowired
    MemberService mService;
    @Autowired
    MailService mailService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ApplyService aService;
    @Autowired
    ClassUnitService unitService;

    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    final String format = "RestMemberController => {}";

    @GetMapping(value = "/idcheck.json")
    public Map<String, Integer> idcheckGET(@RequestParam(name = "id") String id) {

        // System.out.println(id);

        Map<String, Integer> retMap = new HashMap<>();

        int ret = mService.selectMemberIDCheck(id);

        retMap.put("ret", ret);

        return retMap;
    }

    @GetMapping(value = "/findid.json")
    public Map<String, String> findIdGET(@ModelAttribute Member obj) {

        // System.out.println(obj.toString());

        Map<String, String> retMap = new HashMap<>();

        Member ret = mService.selectFindMemberId(obj);

        // System.out.println(ret.getId());

        if (ret != null) {
            retMap.put("ret", ret.getId());
        } else {
            retMap.put("ret", "not");
        }

        return retMap;
    }

    @PostMapping(value = "/findpw.json")
    public Map<String, Object> findPwPOST(@RequestBody Member obj)
            throws MessagingException, UnsupportedEncodingException {

        log.info(format, obj.getEmail());

        Map<String, Object> retMap = new HashMap<>();

        String email = mailService.sendEmail(obj.getEmail());

        retMap.put("email", email);

        return retMap;

    }

    @PostMapping(value = "/verifyauthnum.json")
    public Map<String, Integer> verifyauthnumPOST(@RequestBody Authnum obj) {

        // log.info(format, obj.toString());
        // log.info(format, obj.getAuthnum());

        Map<String, Integer> retMap = new HashMap<>();

        String authnum = redisUtil.getData(obj.getEmail());
        // log.info(format, authnum);

        if (authnum == null) {
            retMap.put("status", -1);
        }
        if (obj.getAuthnum().equals(authnum)) {
            retMap.put("status", 200);
        }
        if (!obj.getAuthnum().equals(authnum)) {
            retMap.put("status", 0);
        }

        return retMap;
    }

    @PutMapping(value = "/findpwaction.json")
    public Map<String, Object> findpwactionPUT(@RequestBody Member obj) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, obj.getPassword());

        try {
            Member member = mService.selectMemberOne(obj.getId());

            // log.info(format, member.toString());

            member.setPassword(bcpe.encode(obj.getPassword()));

            int ret = mService.updateMemberPassword(member);

            retMap.put("ret", ret);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("ret", -1);
        }

        return retMap;
    }

    @PostMapping(value = "/verifyPw.json")
    public Map<String, Object> verifyPwPOST(@RequestBody Member obj) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, obj.getPassword());

        Member ret = mService.selectMemberOne(obj.getId());

        retMap.put("ret", 0);

        if (bcpe.matches(obj.getPassword(), ret.getPassword())) {
            retMap.put("ret", 1);
        }

        return retMap;

    }

    @PutMapping(value = "/updateinfo.json")
    public Map<String, Object> updateinfoPUT(@RequestBody Member obj) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, obj.toString());

        int ret = mService.updateMemberOne(obj);

        retMap.put("ret", ret);

        if (ret == 1) {
            Member member = mService.selectMemberOne(obj.getId());
            retMap.put("member", member);
        }

        return retMap;
    }

    @PutMapping(value = "/updatepw.json")
    public Map<String, Object> updatepwPUT(@RequestBody Member obj) {

        Map<String, Object> retMap = new HashMap<>();

        Member member = mService.selectMemberOne(obj.getId());

        if (bcpe.matches(obj.getPassword(), member.getPassword())) {

            obj.setPassword(bcpe.encode(obj.getNewpassword()));
            int ret = mService.updateMemberPassword(obj);

            if (ret == 1) {

                retMap.put("status", 200);

            } else {
                retMap.put("status", 0);
            }

        } else {
            retMap.put("status", -1);
        }

        return retMap;
    }

    @PutMapping(value = "/signout.json")
    public Map<String, Object> signoutPUT(@RequestBody Member obj) {

        Map<String, Object> retMap = new HashMap<>();

        // 1. 비밀번호 일치 여부 확인

        Member member = mService.selectMemberOne(obj.getId());

        if (bcpe.matches(obj.getPassword(), member.getPassword())) {

            int cntCheck = unitService.selectUnitViewCntCheck(obj.getId());
            long applyCheck = aService.countApplyListOne(obj.getId());

            //log.info(format, cntCheck);
            //log.info(format, applyCheck);

            // 2. 등록된 ClassUnit 신청 이력 확인 ( cnt > 0이면, 탈퇴 불가 )

            if (cntCheck > 0) {

                retMap.put("status", -1);
                retMap.put("errormessage", "신청 내역이 존재하는 클래스 일정이 있습니다. 탈퇴가 불가능합니다.");

            }

            // 3. 결제 내역 확인 ( applychk = 2인 내역이 존재하면, 탈퇴 불가 )

            else if(cntCheck == 0 && applyCheck > 0) {

                retMap.put("status", -1);
                retMap.put("errormessage", "실시되지 않은 클래스 결제 내역이 존재합니다. 탈퇴가 불가능합니다.");
            }

            else if(cntCheck == 0 && applyCheck == 0) {

                // 3. 회원 탈퇴 처리
                int ret = mService.deleteMemberOne(obj);

                if (ret == 1) {

                    retMap.put("status", 200);

                } else {

                    retMap.put("status", 0);

                }
            }

        } else {

            retMap.put("status", -1);
            retMap.put("errormessage", "비밀번호가 일치하지 않습니다.");

        }

        return retMap;

    }

    @GetMapping(value = "/selectlist.json")
    public Map<String, Object> selectlistGET(
            @RequestParam(name = "menu", defaultValue = "0") int menu,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @AuthenticationPrincipal User user) {

        Map<String, Object> map = new HashMap<>();
        String id = user.getUsername();
        int first = page * 5 - 4;
        int last = page * 5;
        long cnt = aService.countApplyList(id);
        List<ApplyView> list = new ArrayList<>();

        long chk1 = 0;
        long chk2 = 0;
        long chk3 = 0;

        chk1 = aService.countApplyListOne(id);
        chk2 = aService.countApplyListTwo(id);
        chk3 = aService.countApplyListThree(id);

        map.put("chk1", chk1);
        map.put("chk2", chk2);
        map.put("chk3", chk3);

        map.put("id", id);
        map.put("first", first);
        map.put("last", last);

        log.info(format, "map=", map.toString());

        list = aService.selectApplyListById(map);
        log.info(format, "list=", list.toString());

        map.put("list", list);
        map.put("pages", (cnt - 1) / 5 + 1);
        map.put("status", 200);

        return map;
    }

    @GetMapping(value = "/selectstatuslist.json")
    public Map<String, Object> selectstatuslistGET(
            @RequestParam(name = "no", defaultValue = "0") int no,
            @AuthenticationPrincipal User user) {

        Map<String, Object> map = new HashMap<>();

        List<ApplyStatusView> list = new ArrayList<>();

        String id = user.getUsername();

        map.put("id", id);
        map.put("no", no);

        list = aService.selectApplyStatusListById(map);
        map.put("list", list);
        map.put("okok", "okokok");

        // log.info(format, id);
        // log.info(format, no);
        // log.info(format, list);

        return map;

    }

}
