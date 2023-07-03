package com.example.service.classproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ClassAnswer;
import com.example.entity.ClassInquiry;
import com.example.entity.Notification;
import com.example.repository.ClassAnswerRepository;
import com.example.repository.ClassInquiryRepository;
import com.example.repository.NotificationRepository;

@Service
public class ClassAnswerServiceImpl implements ClassAnswerService {

    @Autowired ClassAnswerRepository cAnswerRepository;
    @Autowired ClassInquiryRepository cInquiryRepository;
    @Autowired NotificationRepository notificationRepository;

    @Override
    public int insertClassAnswerOne(ClassAnswer obj) {
        try {

            // 1. 답변 등록

            ClassInquiry classinquiry = cInquiryRepository.findById(obj.getNo()).orElse(null);
            classinquiry.setChk(1);

            obj.setClassinquiry(classinquiry);

            cAnswerRepository.save(obj);

            // 2. 답변 알림 등록 (To. 문의자)

            Notification notification = new Notification();

            notification.setType("문의");
            notification.setMember(obj.getClassinquiry().getMember());
            notification.setContent("문의하신 내용에 대한 답변이 등록됐습니다.");
            notification.setUrl("/member/mypage.do?menu=3");

            notificationRepository.save(notification);

            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ClassAnswer selectClassAnswerOne(long inquiryno) {
        try {

            ClassAnswer obj = cAnswerRepository.findById(inquiryno).orElse(null);

            return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectClassAnswerCount(long no) {
        try {
            return cAnswerRepository.countByNo(no);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateClassAnswerOne(ClassAnswer obj) {
        try {

            // 1. 답변 번호로 DB에 존재하는 기존 답변 객체 조회

            ClassAnswer classAnswer = cAnswerRepository.findById(obj.getNo()).orElse(null);
            
            // 2. 답변 객체 수정

            classAnswer.setTitle(obj.getTitle());
            classAnswer.setContent(obj.getContent());
            
            // 3. 수정된 답변 객체 저장

            cAnswerRepository.save(classAnswer);

            // 4. 답변 수정 알림 전송

            ClassInquiry classinquiry = cInquiryRepository.findById(obj.getNo()).orElse(null);

            Notification notification = new Notification();

            notification.setType("문의");
            notification.setMember(classinquiry.getMember());
            notification.setContent("문의하신 내용에 대한 답변이 수정됐습니다.");
            notification.setUrl("/member/mypage.do?menu=3");

            notificationRepository.save(notification);

            // 5. 결과 반환
            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
