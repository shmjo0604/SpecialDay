package com.example.dto;

import lombok.Data;

@Data
public class ActDetailCate {

    // 클래스 하위 분류 번호 (PK)
    private int code; 
    // 클래스 하위 분류 명칭
    private String actcate;
    // 클래스 상위 분류 번호 (FK)
    private int actcode;

}
