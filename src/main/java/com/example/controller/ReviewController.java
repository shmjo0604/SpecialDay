package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Review;
import com.example.entity.ReviewImage;
import com.example.service.review.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    @Autowired
    ResourceLoader resourceLoader;
    @Value("${default.image}")
    String defaultImg;

    final ReviewService reviewService;
    final String format = "reviewController => {}";

    // 리뷰 내용, 리뷰 이미지 데이터 추가
    @PostMapping(value = "insert.do")
    public String insertPOST(@ModelAttribute Review review,
            @RequestParam(name = "files", required = false) List<MultipartFile> files,
            HttpSession httpSession)
            throws IOException {

        // long no = review.getNo();

        // log.info(format, "review=" + review.toString());
        List<ReviewImage> list = new ArrayList<>();

        for (MultipartFile multipartfile : files) {

            if (multipartfile.getSize() > 0) {

                ReviewImage obj = new ReviewImage();

                obj.setReview(review);
                obj.setFilesize(multipartfile.getSize());
                obj.setFiledata(multipartfile.getInputStream().readAllBytes());
                obj.setFiletype(multipartfile.getContentType());
                obj.setFilename(multipartfile.getOriginalFilename());

                list.add(obj);

                // System.out.println(obj.getFilename());
                // System.out.println(obj.getFilesize());
            }
        }

        int result1 = reviewService.insertReview(review);

        // log.info(format, list.toString());

        if(result1 == 1) {

            int result2 = reviewService.insertReviewImage(list);

            if(result2 > 0) {

                httpSession.setAttribute("alertMessage", "리뷰가 정상적으로 등록됐습니다. 리뷰 내역으로 이동합니다.");
                httpSession.setAttribute("alertUrl", "/member/mypage.do?menu=2&page=1");
                

            }

            else {

                httpSession.setAttribute("alertMessage", "리뷰 이미지 등록에 실패했습니다.");
                httpSession.setAttribute("alertUrl", "/member/mypage.do?menu=1&page=1");
                
            }

        }
        
        return "redirect:/alert.do";
    }

    // 이미지 뛰우기
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> classImage(
        @RequestParam(name = "no", defaultValue = "0", required = false) long no
        ) throws IOException {

            //log.info(format, no);

            ReviewImage obj = reviewService.selectReivewImageOne(no);

            //log.info(format, obj.toString());

            HttpHeaders headers = new HttpHeaders();

            if(obj != null) {

                if(obj.getFilesize() > 0) {

                    headers.setContentType(MediaType.parseMediaType(obj.getFiletype()));

                    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(obj.getFiledata(), headers, HttpStatus.OK);
                    
                    return response;
                }
            }

            InputStream is = resourceLoader.getResource(defaultImg).getInputStream();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(is.readAllBytes(), headers, HttpStatus.OK);
        }

    // 고객센터 페이지
    @GetMapping(value = "/customercenter.do")
    public String customercenterGET(
            @RequestParam(name = "menu", defaultValue = "0") int menu,
            @AuthenticationPrincipal User user,
            Model model) {

        String id = user.getUsername();

        if (menu == 0) {
            return "redirect:/review/customercenter.do?menu=1";
        }

        if (menu == 1) {

        }

        else if (menu == 2) {

        }
        model.addAttribute("user", user);

        return "/review/customercenter";
    }

}
