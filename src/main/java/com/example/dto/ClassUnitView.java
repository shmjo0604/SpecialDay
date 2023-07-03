package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClassUnitView {

    // 클래스 코드 (sequence)
    private long classcode;
	// 제목
	private String title;
	// 우편번호
	private String postcode;
	// 도로명주소, 지번주소
	private String address1;
	// 상세주소
	private String address2;
	// 참고항목
	private String address3;
	// 위도
	private String latitude;
	// 경도
	private String longitude;
	// 가격
	private int price;
	// 소개글
	private String intro;
	// 상호명
	private String nickname;
	// 커리큘럼
	private String curriculum;
	// 조회수
	private long hit;
	// 등록일자
	private Date regdate;
	// 승인여부 (0, 1)
	private int chk;        
	// 지역 하위 분류 코드
	private int localcode;
	// 클래스 하위 분류 코드
	private int actdetailcode;
	// 회원 아이디
	private String memberid;
    // 유닛 번호 (sequence)
	private long no;
	// 현재 신청 인원
	private int cnt;
	// 최소 신청 인원
	private int minimum;
	// 최대 신청 인원
	private int maximum;
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
	// 등록일자
	private Date unitregdate; 
	// 지역 하위 분류 명칭
	private String localcate;
	// 지역 상위 분류 코드
	private int citycode;
    // 지역 상위 분류 명칭
	private String citycate;
	// 클래스 하위 분류 명칭
	private String actdetailcate;
	// 클래스 상위 분류 코드
	private int activitycode;
	// 클래스 상위 분류 명칭
	private String activitycate;
	// 1인당 총 가격 => ( 기본 가격 + 추가 가격 ) * (1 - 할인율)
	private int totalprice;
    
	// 클래스 메인 이미지 번호
	private long mainImg;
	// 클래스 프로필 이미지 번호
	private long profileImg;
	// 신청할 인원수
	private int person;
	// 각 유닛별 결제 금액
	private int payment;
}
