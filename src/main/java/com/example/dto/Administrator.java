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
public class Administrator {

    // 아이디 (PK)
    private String id;
    // 비밀번호
    private String password;
    // 이름
    private String name;
    // 가입일자
    private Date regdate;
    
}