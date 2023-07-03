package com.example.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 클래스 이미지
@Getter
@Setter
@ToString(exclude = {"filedata"})
@NoArgsConstructor
@AllArgsConstructor
public class ClassImage {
	
	// 클래스 이미지 번호 (sequence) (PK)
	private long no;
	// 파일명
	private String filename;
	// 파일 크기
	private long filesize;
	// 파일 데이터
	private byte[] filedata;
	// 파일 타입
	private String filetype;
	// 클래스 이미지 종류 => ( 1:프로필이미지, 2:메인, 3:서브, 4:커리큘럼 )
	private int typechk;
	// 이미지 등록일자
	private Date regdate;
	// 클래스 코드(FK)
	private long classcode;
    public void add(ClassImage classSub) {
    }
	
}
