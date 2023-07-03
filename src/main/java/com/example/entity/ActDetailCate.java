package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "ACTDETAILCATE")
@Entity
public class ActDetailCate {

  // 101,102,103...
  @Id
  private int code;

  @Column(nullable = false)
  private String actcate;

  // 액티비티카테고리테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ACTCODE", referencedColumnName = "CODE")
  private ActivityCate activitycate;

  // 클래스 상품 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "actdetailcate", fetch = FetchType.LAZY)
  private List<ClassProduct> classproductList = new ArrayList<>();
}
