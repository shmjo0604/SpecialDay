package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "CLASSUNIT")
@Entity
@SequenceGenerator(name = "SEQ_CLASSUNIT_NO", sequenceName = "SEQ_CLASSUNIT_NO", initialValue = 1, allocationSize = 1)
public class ClassUnit {

  // 유닛 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASSUNIT_NO")
  private long no;

  // 현재신청인원
  private int cnt;
  
  // 최소신청인원
  private int minimum;
  
  // 최대신청인원
  private int maximum;
  
  // 날짜
  @Column(nullable = false)
  private String classdate;
  
  // 요일
  private String classday;
  
  // 오전 6:00
  @Column(nullable = false)
  private String classstart;
  
  // 오후 9:00
  @Column(nullable = false)
  private String classend;
  
  // 클래스 난이도(입문자, 경험자, 숙련자)
  private int classlevel;
  
  // 할인률
  private int discount;
  
  // 클래스 추가금액
  private int addprice;
  
  // 유닛 상태(운영 / 삭제)
  private int chk=1;
  
  // 등록일자
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;
  
  // 클래스 상품 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @JoinColumn(name = "classcode", referencedColumnName = "CLASSCODE")
  private ClassProduct classproduct;
  
  // 신청 테이블
  @ToString.Exclude
  @JsonIgnore
  @OneToMany(mappedBy = "classunit", fetch = FetchType.LAZY)
  private List<Apply> applyList = new ArrayList<>();
  
  // 장바구니 테이블
  @ToString.Exclude
  @JsonIgnore
  @OneToMany(mappedBy = "classunit", fetch = FetchType.LAZY)
  private List<Basket> basketList = new ArrayList<>();

}
