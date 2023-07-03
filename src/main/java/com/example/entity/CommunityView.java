package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "COMMUNITYVIEW")
public class CommunityView {

    @Id
    private long no;

    private String cate;

    // 글 제목
    private String title;

    // 글 내용
    @Lob
    private String content;

    // 글 조회수
    private int hit;

    private Date regdate;

    private int replycnt;

    private String memberid;

    private long rnum;

}
