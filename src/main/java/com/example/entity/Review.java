package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "REVIEW")
@Entity
public class Review {

  // 신청번호(applyno)
  @Id
  @Column(nullable = false)
  private long no;

  // 내용
  @Lob
  private String content;

  // 평점(1,2,3,4,5)
  private int rating;

  // 리뷰등록일자
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(insertable = true, updatable = false)
  private Date regdate;

  // 추천수
  private int hit;

  // 신청 테이블
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "no", referencedColumnName = "NO")
  @MapsId
  private Apply apply;

  // 리뷰이미지
  @ToString.Exclude
  @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
  private List<ReviewImage> reviewimageList = new ArrayList<>();
}
