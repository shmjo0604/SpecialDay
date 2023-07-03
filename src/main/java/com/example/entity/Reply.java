package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "REPLY")
@Entity
@SequenceGenerator(name = "SEQ_REPLY_NO", sequenceName = "SEQ_REPLY_NO", initialValue = 1, allocationSize = 1)
public class Reply {

  // 댓글 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REPLY_NO")
  private long no;

  // 댓글 내용
  @Lob
  private String content;

  // 답글깊이
  private int repdepth;
  //0으로 들어가야함 => 일반 댓글인경우
  //1로 들어가야 함 => 대댓글


  // 답글순서
   //기본값이 0
  private int reporder;
 

  // 부모답글번호
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REPLY_NO")
  private int parentno;

  //id 값이랑 부모 답글번호가 같은지 확인



  // 등록 일자
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(insertable = true, updatable = false)
  private Date regdate;

  // 커뮤니티
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "communityno", referencedColumnName = "NO")
  private Community community;

  // 회원 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberid", referencedColumnName = "ID")
  private Member member;
}
