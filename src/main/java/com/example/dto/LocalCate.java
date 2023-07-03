package com.example.dto;

import lombok.Data;

@Data
public class LocalCate {
    
    // 상세 지역 코드 (sequence) (PK)
    private int code;
    // 상세 지역명
	private String localcate;
    // 지역 코드 (FK)
	private int citycode;
    
}
