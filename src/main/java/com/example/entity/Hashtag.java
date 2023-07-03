package com.example.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "HASHTAG")
@Entity
@SequenceGenerator(name = "SEQ_HASHTAG_NO", sequenceName = "SEQ_HASHTAG_NO", initialValue = 1, allocationSize = 1)
public class Hashtag {

  // 해시태그 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HASHTAG_NO")
  private long no;

  // 해시태그 이름
  private String name;

  // 클래스 + 해시태그 테이블
  @ToString.Exclude
  @OneToMany(mappedBy = "hashtag", fetch = FetchType.LAZY)
  private List<Classtag> classtagList = new ArrayList<>();
  
}
