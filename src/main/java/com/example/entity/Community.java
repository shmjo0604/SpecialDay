package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "COMMUNITY")
@Entity
@SequenceGenerator(name = "SEQ_COMMUNITY_NO", sequenceName = "SEQ_COMMUNITY_NO", initialValue = 1, allocationSize = 1)
public class Community {

  // 글 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMMUNITY_NO")
  private long no;

  // 글 분류(궁금해요, 함께해요, 추천해요,양도해요 등)
  private String cate;

  // 글 제목
  private String title;

  // 글 내용
  @Lob
  private String content;

  // 글 조회수
  private int hit  = 1;

  // 등록 일자
  @UpdateTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private Date regdate;

  // 댓글 수
  private int replycnt;

  // 회원 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberid", referencedColumnName = "ID")
  private Member member;

  // 댓글
  @ToString.Exclude
  @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties({"board"})
  private List<Reply> replyList = new ArrayList<>();

}
