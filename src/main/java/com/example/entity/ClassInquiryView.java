package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "CLASSINQUIRYVIEW")
public class ClassInquiryView {
    
    @Id
    @Column(name = "NO")
    private long no;

    @Column(name = "TITLE")
    private String title;
    
    @Lob
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CHK")
    private int chk;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "REGDATE")
    private Date regdate;

    @Column(name = "CLASSTITLE")
    private String classtitle;

    @Column(name = "CLASSCODE")
    private long classcode;

    @Column(name = "MEMBERID")
    private String memberid;

    @Column(name = "OWNER")
    private String owner;

}
