package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ReviewImageProjection;
import com.example.entity.ReviewView;
import com.example.service.review.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/review")
public class RestReviewController {

    final String format = "RestReviewController => {}";
    
    @Autowired ReviewService rService;

    @Value("${productreivew.page}") int reviewpaging;

    @GetMapping(value = "/selectlist.json")
    public Map<String, Object> selectlistGET(
        @RequestParam(name = "classcode") long classcode,
        @RequestParam(name = "page") int page) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, classcode);

        long count = rService.countByClasscode(classcode);

        // log.info(format, count);

        long pages = ((count-1)/reviewpaging) + 1;

        int first = (page * reviewpaging) - (reviewpaging-1);
        int last = page * reviewpaging;

        retMap.put("status", -1);

        List<ReviewView> list = rService.selectByClasscode(classcode, first, last);

        // log.info(format, list.toString());

        if(!list.isEmpty()) {

            for(ReviewView reviewView : list) {

                long reviewno = reviewView.getNo();

                List<ReviewImageProjection> imgList = rService.selectReviewImageNoList(reviewno);

                reviewView.setImglist(imgList);
                
                // for(ReviewImageProjection one : imgList) {
                //     log.info(format, one.getNo()); 
                // }
            }

            retMap.put("status", 200);
            retMap.put("list", list);
        }

        retMap.put("pages", pages);

        return retMap;
    }

    @GetMapping(value = "/selectreviewOne.json")
    public Map<String, Object> selectreviewOneGET(
            @RequestParam(name = "no", defaultValue = "0") long no,
            @AuthenticationPrincipal User user,
            Model model) {

        Map<String, Object> map = new HashMap<>();
        String id = user.getUsername();
        ReviewView review = rService.selectReviewOne(id, no);

        List<Long> reviewNolist = rService.reviewImagelistNo(no);

        map.put("review", review);
        map.put("reviewNolist", reviewNolist);

        return map;

    }

    @PutMapping(value = "/updatehit.json")
    public Map<String, Object> updatehitPUT(@RequestBody com.example.entity.Review obj) {

        Map<String, Object> retMap = new HashMap<>();

        log.info(format, obj.toString());
        // log.info(format, obj.getNo());

        int ret = rService.updateReviewHit(obj.getNo());

        retMap.put("ret", ret);

        return retMap;

    }


}
