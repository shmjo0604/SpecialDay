package com.example.dto;

import lombok.Data;

@Data
public class Apply {
    
    // 신청 번호(sequence) (PK)
	private long no;
	// 신청 참여 인원
	private int person;
	// 결제 금액
	private int payment;
	// 최종 신청 상태 확인 -> 1 : 결제 완료(default) / 2 : 결제 취소 / 3 : 참여 완료
	private int chk; 
	// 회원 아이디(구매자) (FK)
	private String memberid;
	// 유닛 번호 (FK)
	private long unitno;

	// 클래스 등록자 아이디(판매자)
	private String ownerid;
	// 클래스 코드
	private long classcode;
}
