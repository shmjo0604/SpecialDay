package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ApplyStatus {

    // 신청 상태 번호 (sequence) (PK)
    private long no;
    // 신청 처리상태 ( 1:결제완료, 2:결제취소, 3:참여완료 )
    private int chk;
    // 신청 상태 등록 일자
    private Date regdate;
    // 신청 번호 (FK)
    private long applyno;

}
