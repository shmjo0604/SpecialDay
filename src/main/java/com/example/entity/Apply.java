package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "APPLY")
@Entity
@SequenceGenerator(name = "SEQ_APPLY_NO", sequenceName = "SEQ_APPLY_NO", initialValue = 1, allocationSize = 1)
public class Apply {

  // 신청 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLY_NO")
  private long no;

  // 신청 인원
  private int person;

  // 결제 금액
  private int payment;

  // 최종 처리 상태(1, 2, 3)
  private int chk;

  // 신청 날짜
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;

  // 회원 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MEMBERID", referencedColumnName = "ID")
  private Member member;

  // 클래스 유닛(일정) 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "UNITNO", referencedColumnName = "NO")
  private ClassUnit classunit;

  // 리뷰 테이블
  @ToString.Exclude
  @OneToOne(mappedBy = "apply", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Review review;

  // 신청 상태 테이블(기록용)
  @ToString.Exclude
  @OneToMany(mappedBy = "apply", fetch = FetchType.LAZY)
  private List<ApplyStatus> applytatusList = new ArrayList<>();
}
