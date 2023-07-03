package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
@Table(name = "CLASSTAG")
@Entity
@SequenceGenerator(name = "SEQ_CLASSTAG_NO", sequenceName = "SEQ_CLASSTAG_NO", initialValue = 1, allocationSize = 1)
public class Classtag {

  // 고유 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASSTAG_NO")
  private long no;

  // 등록 일자
  @UpdateTimestamp // 변경 시에도 날짜 정보 변경
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private Date regdate;

  // 클래스 상품 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "classcode", referencedColumnName = "CLASSCODE")
  private ClassProduct classproduct;

  // 해시태그
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tagno", referencedColumnName = "NO")
  private Hashtag hashtag;
}