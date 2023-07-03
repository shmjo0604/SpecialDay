package com.example.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"password"})
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    // 아이디 (PK)
    private String id;
    // 비밀번호
    private String password;
    // 이름
    private String name;
    // 이메일
    private String email;
    // 전화번호
    private String phone;
    // 성별(M,W)
    private String gender;
    // 생년월일
    private String birth;
    // 가입일자
    private Date regdate;
    // 탈퇴여부(0,1)
    private int chk;
    
    // 신규 비밀번호
    private String newpassword;
    
}
