package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "ACTIVITYCATE")
@Entity
@SequenceGenerator(name = "SEQ_ACTIVITYCATE_NO", sequenceName = "SEQ_ACTIVITYCATE_NO", initialValue = 1, allocationSize = 1)
public class ActivityCate {

  // 1,2,3...
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACTIVITYCATE_NO")
  private int code;

  // 재능(예술, 스포츠, 뷰티, 쿠킹, 쥬얼리, 인테리어)
  @Column(nullable = false)
  private String cate;

  // 액티비티디테일카테고리
  @ToString.Exclude
  @OneToMany(mappedBy = "activitycate", fetch = FetchType.LAZY)
  private List<ActDetailCate> actdetailcateList = new ArrayList<>();

}
