package com.example.service.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Notification;
import com.example.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired NotificationRepository nRepository;

    @Override
    public List<Notification> selectNotificationList(String id) {
    
        try {

            List<Notification> list = nRepository.selectByMemberidOrderByNoDescLimit10(id);

            return list;

        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int updateNotificationChk(long no) {
        try {

            Notification obj = nRepository.findById(no).orElse(null);
            obj.setChk(1);

            nRepository.save(obj);
            return 1;

        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int selectNotificationCountNotRead(String id) {
        try {
            return nRepository.countByMember_idAndChk(id, 0);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
