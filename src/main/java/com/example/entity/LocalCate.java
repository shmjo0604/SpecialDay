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
@Table(name = "LOCALCATE")
@Entity
public class LocalCate {

  @Id
  private int code;

  @Column(nullable = false)
  private String localcate;

  // 지역카테고리테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "citycode", referencedColumnName = "CODE")
  private CityCate citycate;

  // 클래스 상품 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "localcate", fetch = FetchType.LAZY)
  private List<ClassProduct> classproductList = new ArrayList<>();

}
