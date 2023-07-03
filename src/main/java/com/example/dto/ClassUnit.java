package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClassUnit {
  // 클래스유닛 번호 (sequence) (PK)
  private long no;
  // 현재신청인원
  private int cnt;
  // 최소신청인원
  private int minimum;
  // 최대신청인원
  private int maximum;
  // 날짜
  private String classdate;
  // 요일
  private String classday;
  // 시작 시간 (06:00)
  private String classstart;
  // 종료 시간 (21:00)
  private String classend;
  // 할인률
  private int discount;
  // 클래스 추가금액
  private int addprice;
  // 클래스 난이도 => (1 : 입문자, 2 : 경험자, 3 : 숙련자)
  private int classlevel;
  // 등록일자
  private Date regdate;
  // 삭제 확인(default -> 1, 삭제 시 -> 0)
  private int chk;
  // 클래스 코드 (FK)
  private long classcode;

  // row number
  private long rnum;
}
