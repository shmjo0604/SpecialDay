package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClassInquiry {

    private long no;

    private String title;

    private String content;

    private int chk;

    private Date regdate;

    private long classcode;

    private String memberid;
    
}
