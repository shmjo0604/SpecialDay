package com.example.service.classproduct;

import org.springframework.stereotype.Service;

import com.example.entity.ClassAnswer;

@Service
public interface ClassAnswerService {
    
    // 1. 답변 등록
    public int insertClassAnswerOne(ClassAnswer obj);

    // 2. 답변 조회
    public ClassAnswer selectClassAnswerOne(long inquiryno);

    // 3. 답변 존재 유무 확인
    public int selectClassAnswerCount(long no);

    // 4. 답변 수정
    public int updateClassAnswerOne(ClassAnswer obj);

}
