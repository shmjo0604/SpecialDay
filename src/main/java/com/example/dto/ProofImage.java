package com.example.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//자격증, 수료증, 인증서
@Getter
@Setter
@ToString(exclude = {"filedata"})
@NoArgsConstructor
@AllArgsConstructor
public class ProofImage {

	// 파일 번호 (sequence) (PK)
	private long no;
	// 파일이름
	private String filename;
	// 파일사이즈
	private long filesize;
	// 파일데이터
	private byte[] filedata;
	// 파일타입
	private String filetype;
	// 등록일자
	private Date regdate;
	// 클래스 코드 (FK)
	private long classcode;

}
