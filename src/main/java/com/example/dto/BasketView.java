package com.example.dto;

import lombok.Data;

@Data
public class BasketView {
    
    // 번호(no)
    private long no;
    // 인원수
    private long cnt;
    // member id
    private String memberid;
    // 유닛 번호
    private long unitno;
    // member 닉네임(이름)
    private String membername;
    //클래스제목
    private String title;
    // 실시 날짜
    private String classdate;
    // 시작 시간
    private String classstart;
    // 종료 시작
    private String classend;
    // 클래스 난이도(입문자, 경험자)
    private int classlevel;
    // 총가격
    private int totalprice;
    
}
