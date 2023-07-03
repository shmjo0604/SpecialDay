package com.example.entity;

import java.util.Date;

public interface ClassInquiryProjection {
    
    // 문의 번호
    long getNo();

    // 문의 제목
    String getTitle();

    // 문의 내용
    String getContent();

    // 처리 상태
    int getChk();

    // 문의 날짜
    Date getRegdate();

}
