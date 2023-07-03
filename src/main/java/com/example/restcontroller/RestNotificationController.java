package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Notification;
import com.example.service.notification.NotificationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/notification")
public class RestNotificationController {

    final String format = "RestNotificationController => {}";

    @Autowired NotificationService nService;

    @GetMapping(value = "/select.json")
    public Map<String, Object> selectGET(@AuthenticationPrincipal User user) {

        Map<String, Object> retMap = new HashMap<>();

        // log.info(format, user.getUsername());

        retMap.put("status", -1);

        if(user != null) {

            List<Notification> list = nService.selectNotificationList(user.getUsername());
            int count = nService.selectNotificationCountNotRead(user.getUsername());

            // log.info(format, list);

            if(!list.isEmpty()) {

                retMap.put("status", 200);
                retMap.put("list", list);
                retMap.put("count", count);

            }

        }

        return retMap;
    }

    @PutMapping(value = "/updatechk.json")
    public Map<String, Object> updatechkPUT(@RequestBody Notification obj) {

        Map<String, Object> retMap = new HashMap<>();

        //log.info(format, obj.toString());

        int ret = nService.updateNotificationChk(obj.getNo());

        retMap.put("ret", ret);

        return retMap;

    }
    
}
