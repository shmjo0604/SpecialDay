package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Community;
import com.example.entity.CommunityView;
import com.example.entity.Reply;
import com.example.repository.CommunityRepository;
import com.example.repository.ReplyRepository;
import com.example.service.community.CommunityService;
import com.example.service.reply.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/community")
@RequiredArgsConstructor
public class CommunityController {

    final String format = "커뮤니티컨트롤러 => {}";

    final CommunityRepository communityRepository;
    final ReplyRepository rRepository;
    final CommunityService communityService;
    final ReplyService rService;

    @Value("${communitylist.page}")
    int paging;

    // 커뮤니티 글작성
    @GetMapping(value = "/insert.do")
    public String insertGET(
            @AuthenticationPrincipal User user,
            Model model) {

        if (user != null) {
            model.addAttribute("user", user);

        }

        return "/community/insert";
    }

    @PostMapping(value = "/insert.do")
    public String insertPOST(
            @ModelAttribute Community community,
            @AuthenticationPrincipal User user, Model model) throws IOException {

        if (user != null) {

            Community ret = communityRepository.save(community);
            // System.out.println(ret);

        }
        return "redirect:/community/selectlist.do";
    }

    // 커뮤니티 게시판 보기
    @GetMapping(value = "/selectlist.do")
    public String selectlistGET(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(name = "type", defaultValue = "title") String type,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "text", defaultValue = "") String text) {

        if(page == 0) {
            return "redirect:/community/selectlist.do?page=1";
        }

        long count = communityService.countCommunityList();
                
        // log.info(format, count);

        long pages = (count-1)/paging + 1;

        long first = (page * paging) - (paging-1);
        long last = page * paging;

        // List<Community> list = communityService.selectCommunityList(first, last);
        List<CommunityView> list = communityService.selectCommunityList(first, last);

        // for (Community obj : list) {
        //     log.info(format, obj.toString());
        // }

        if (user != null) {
            model.addAttribute("user", user);
            
        }

        model.addAttribute("pages", pages);
        model.addAttribute("list", list);

        return "/community/selectlist";

    }

    // 커뮤니티 게시판글 보기
    @GetMapping(value = "/selectone.do")
    public String selectoneGET(Model model, @RequestParam(name = "no") long no,
    @AuthenticationPrincipal User user) {

        Community community = communityRepository.findByNo(no);
        List<Reply> list = rRepository.findByCommunity_noOrderByNoDesc(no);

        // if (user == null) {
        //    return "redirect:/login.do";
        // }

        // log.info(format, list);
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        model.addAttribute("community", community);

        return "/community/selectone";

    }

    @RequestMapping(value = "/delete.do", method = { RequestMethod.POST })
    public String deleteGET(@AuthenticationPrincipal User user, @ModelAttribute Community obj,
            Model model, HttpSession httpSession) {
        try {

            // log.info(format, obj.toString());
            communityRepository.deleteById(obj.getNo());

            httpSession.setAttribute("alertMessage", "삭제되었습니다.");
            httpSession.setAttribute("alertUrl", "/community/selectlist.do");

            return "redirect:/alert.do";

        } catch (Exception e) {
            e.printStackTrace();
            // return "redirect:/home.do";
            return "redirect:/community/selectone.do?no="+obj.getNo();
        }
    }

    @GetMapping(value = "/update.do")
    public String updateGET(@AuthenticationPrincipal User user,
            @RequestParam(name = "no") long no,
            Model model) {
        // log.info(format, no);
        Community com = communityRepository.findById(no).orElse(null);

        // log.info(format, com.toString());

        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("community", com);
        }

        return "community/update";
    }

    @PostMapping(value = "/update.do")
    public String updatePOST(@AuthenticationPrincipal User user, @ModelAttribute Community community,
            @RequestParam(name = "no", defaultValue = "0", required = false) long no)
            throws IOException {

        // log.info("nocheck => {}", community.getNo());

        try {
            Community com = communityRepository.findById(no).orElse(null);

            if (no != 0) {
                com.setCate(community.getCate());
                com.setTitle(community.getTitle());
                com.setContent(community.getContent());

                return "redirect:/community/selectone.do?no=" + no;
            } else {
                return "redirect:/community/selectlist.do";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/community/selectlist.do";
        }

    }

    @GetMapping(value = "/replyinsert.do")
    public String replyinsertGET(@RequestParam(name = "no", defaultValue = "0", required = false) long no,
            @AuthenticationPrincipal User user, Model model) {

        Community community = communityRepository.findById(no).orElse(null);

        if (user != null) {
            model.addAttribute("user", user);
            // System.out.println(user.toString());
        }
        model.addAttribute("community", community);
        return "/community/replyinsert";
    }

    @PostMapping(value = "/replyinsert.do")
    public String replyinsertPOST(
            @ModelAttribute Reply reply, @AuthenticationPrincipal User user, Model model) throws IOException {
        // log.info(format, reply.toString());

        if (reply != null) {
            reply.getContent();

            Reply ret = rRepository.save(reply);
            // System.out.println(ret);

        }
        return "redirect:/community/selectone.do?no=" + reply.getCommunity().getNo();
    }

}
