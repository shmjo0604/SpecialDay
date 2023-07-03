package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "MEMBER")
@Entity
public class Member {

  // 회원아이디
  @Id
  private String id;

  // 회원비밀번호
  @ToString.Exclude
  private String password;

  // 회원이름
  private String name;

  // 회원이메일
  private String email;

  // 회원전화번호
  private String phone;

  // 성별(M,W)
  private String gender;

  // 생년월일
  private String birth;

  // 회원가입일자
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(insertable = true, updatable = false)
  private Date regdate;

  // 회원가입여부(0,1)
  private int chk;

  // 신청 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Apply> applyList = new ArrayList<>();

  // 클래스 상품 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<ClassProduct> classproductList = new ArrayList<>();

  // 장바구니 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Basket> basketList = new ArrayList<>();

  // 커뮤니티
  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Community> communityList = new ArrayList<>();

  // 댓글
  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Reply> replyList = new ArrayList<>();

  // 클래스 문의
  @ToString.Exclude
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<ClassInquiry> classinquiryList = new ArrayList<>();

}
