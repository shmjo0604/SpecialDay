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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "CLASSPRODUCT")
@Entity
@SequenceGenerator(name = "SEQ_CLASSPRODUCT_NO", sequenceName = "SEQ_CLASSPRODUCT_NO", initialValue = 1, allocationSize = 1)
public class ClassProduct {

  // 클래스 코드(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASSPRODUCT_NO")
  private long classcode;

  // 제목
  private String title;

  // 우편번호
  private String postcode;

  // 도로명주소,지번주소
  private String address1;

  // 상세주소
  private String address2;

  // 참고항목
  private String address3;

  // 위도
  private String latitude;

  // 경도
  private String longitude;

  // 가격
  private int price;

  // 상호명
  private String nickname;

  // 소개글
  @Lob
  private String intro;

  // 강사소개
  @Lob
  private String instructor;

  // SNS링크(선택)
  private String sns;

  // 커리큘럼
  @Lob
  private String curriculum;

  // 조회수
  private long hit;

  // 등록일자
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;

  // 승인여부(0, 1) -> 삭제&휴면(2, 3)
  private int chk;

  // 상세지역카테고리테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "localcode", referencedColumnName = "CODE")
  private LocalCate localcate;

  // 회원 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberid", referencedColumnName = "ID")
  private Member member;

  // 액티비티디테일카테고리
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "actdetailcode", referencedColumnName = "CODE")
  private ActDetailCate actdetailcate;

  // 클래스 유닛(일정) 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "classproduct", fetch = FetchType.LAZY)
  private List<ClassUnit> classunitList = new ArrayList<>();

  // 클래스이미지
  @ToString.Exclude
  @OneToMany(mappedBy = "classproduct", fetch = FetchType.LAZY)
  private List<ClassImage> classimageList = new ArrayList<>();

  // 자격증, 수료증, 인증서
  @ToString.Exclude
  @OneToMany(mappedBy = "classproduct", fetch = FetchType.LAZY)
  private List<ProofImage> approvalimageList = new ArrayList<>();

  // 클래스 + 해시태그 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "classproduct", fetch = FetchType.LAZY)
  private List<Classtag> classtagList = new ArrayList<>();

  // 클래스 문의
  @ToString.Exclude
  @JsonIgnore
  @OneToMany(mappedBy = "classproduct", fetch = FetchType.LAZY)
  private List<ClassInquiry> classinquiryList = new ArrayList<>();

  @Transient
  private long mainImg;

  @Transient
  private long profileImg;

}
