package com.example.service.notification;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Notification;

@Service
public interface NotificationService {
    
    // 1. 알림 조회
    public List<Notification> selectNotificationList(String id);

    // 2. 알림 읽음 상태 변경
    public int updateNotificationChk(long no);

    // 3. 안 읽은 알림 개수 조회
    public int selectNotificationCountNotRead(String id);

}
