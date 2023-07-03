package com.example.dto;

import lombok.Data;

@Data
public class ActivityCate {

     // 클래스 상위 분류 번호 (PK)
     private int code;
     // 클래스 상위 분류 명칭(예술, 스포츠, 뷰티, 쿠킹 등)
     private String cate;
    
}
