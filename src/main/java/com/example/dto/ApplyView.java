package com.example.dto;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplyView {

    // 클래스 코드 (sequence)
    private long classcode;
    // 제목
    private String title;
    // 가격
    private int price;
    // 클래스 승인여부 (0, 1)
    private int classchk;
    // 회원 아이디
    private String memberid;
    // 실시 날짜
    private String classdate;
    // 실시 요일
    private String classday;
    // 시작 시간
    private String classstart;
    // 종료 시작
    private String classend;
    // 할인율
    private String discount;
    // 클래스 추가금액
    private int addprice;
    // 클래스 난이도(입문자, 경험자)
    private int classlevel;
    // 1인당 총 가격 => ( 기본 가격 + 추가 가격 ) * (1 - 할인율)
    private int totalprice;
    // paymant(신청테이블)
    private int payment;
    // 유닛 번호
    private long unitno;
    // 클래스 메인 이미지 번호
    private long mainImg;
    // 신청할 인원수
    private int person;
    // 신청날짜
    private Date regdate;
    // 신청번호
    private long no;
    // 유닛 chk
    private int unitchk;
    // 신청 상태
    private int chk;
    /*** 추가 ***/
    // 현재 신청 인원
    private int cnt;
    // 클래스 개설자 id 
    private String ownerid;
    // 신청자 전화번호
    private String phone;


}