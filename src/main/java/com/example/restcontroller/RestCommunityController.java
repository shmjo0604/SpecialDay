package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Community;
import com.example.mapper.CommunityMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value ="/api/community")
@RequiredArgsConstructor
@Slf4j
public class RestCommunityController {
    final String format = "레스트커뮤니티 => {}";
    final CommunityMapper comMapper; // 매퍼 객체 생성

    @PutMapping(value = "/updatehit.json")
    public Map<String, Integer> updatehitPUT(@RequestBody Community community){
        log.info(format, community.toString());
        int ret =comMapper.updatehit(community.getNo());

        Map<String, Integer> rMap = new HashMap<>();

        rMap.put("result", ret);
        return rMap;
    }



}
