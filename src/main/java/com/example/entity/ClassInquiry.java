package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "CLASSINQUIRY")
@Entity
@SequenceGenerator(name = "SEQ_CLASSINQUIRY_NO", sequenceName = "SEQ_CLASSINQUIRY_NO", initialValue = 1, allocationSize = 1)
public class ClassInquiry {

  // 문의번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASSINQUIRY_NO")
  private long no;

  // 문의제목
  private String title;

  // 문의내용
  @Lob
  private String content;

  // 처리상태(답변대기, 답변완료)
  private int chk;

  // 문의날짜
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;

  // 클래스 상품 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "classcode", referencedColumnName = "CLASSCODE")
  private ClassProduct classproduct;

  // 회원 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberid", referencedColumnName = "ID")
  private Member member;

  // 클래스 문의 답변
  @ToString.Exclude
  @OneToOne(mappedBy = "classinquiry", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private ClassAnswer classanswer;
}
