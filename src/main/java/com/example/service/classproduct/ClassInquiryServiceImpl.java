package com.example.service.classproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ClassInquiry;
import com.example.entity.ClassInquiryView;
import com.example.entity.ClassInquiryViewVo;
import com.example.entity.Notification;
import com.example.repository.ClassInquiryRepository;
import com.example.repository.ClassInquiryViewRepository;
import com.example.repository.NotificationRepository;

@Service
public class ClassInquiryServiceImpl implements ClassInquiryService {

    @Autowired ClassInquiryRepository cInquiryRepository;
    @Autowired NotificationRepository notificationRepository;
    @Autowired ClassInquiryViewRepository cInquiryViewRepository;

    @Override
    public int insertClassInquiryOne(ClassInquiry obj) {
        try {

            cInquiryRepository.save(obj);

            Notification notification = new Notification();

            notification.setType("문의");
            notification.setContent("문의가 접수되었습니다.");
            notification.setUrl("/member/myclass.do?menu=2");
            notification.setMember(obj.getClassproduct().getMember());

            notificationRepository.save(notification);

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ClassInquiry> selectClassInquiryList(long classcode) {
        try {
            return cInquiryRepository.findByClassproduct_classcodeOrderByNoDesc(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long selectCountClassInquiryList(long classcode) {
        try {
            return cInquiryRepository.countByClassproduct_classcode(classcode);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ClassInquiryView selectClassInquiryViewOne(long no) {
        try {
            return cInquiryViewRepository.findByNo(no);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ClassInquiryViewVo> selectClassInquiryListByMemberid(String id, int first, int last) {
        try {
            return cInquiryViewRepository.selectClassInquiryListByMemberid(id, first, last);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long selectClassInquiryCountByMemberid(String id) {
        try {
            return cInquiryRepository.countByMember_id(id);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long selectClassInquiryCountByidAndChk(String id, int chk) {
        try {
            return cInquiryRepository.countByMember_idAndChk(id, chk);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ClassInquiryViewVo> selectByMemberidAndChk(String id, int chk, int first, int last) {
        try {
            List<ClassInquiryViewVo> list = cInquiryViewRepository.selectByMemberidAndChk(id, chk, first, last);
            return list;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
}
