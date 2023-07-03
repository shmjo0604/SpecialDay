package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "REVIEWVIEW")
public class ReviewView {

    @Id
    @Column(name = "NO")
    private long no;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "REGDATE")
    private Date regdate;

    @Column(name = "HIT")
    private int hit;

    // 임의 추가
    @Column(name = "ID")
    private String id;

    @Column(name = "CLASSCODE")
    private long classcode;

    @Column(name = "TITLE")
    private String title;

    // 리뷰모달 창에 들어갈 값 추가
    @Column(name = "CLASSDATE")
    private String classdate;

    @Column(name = "CLASSSTART")
    private String classstart;

    @Column(name = "CLASSEND")
    private String classend;

    @Column(name = "CLASSLEVEL")
    private int classlevel;

    @Column(name = "PRICE")
    private int price;

    @Transient
    private List<ReviewImageProjection> imglist;


}
