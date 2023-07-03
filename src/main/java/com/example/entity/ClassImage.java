package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "CLASSIMAGE")
@Entity
@SequenceGenerator(name = "SEQ_CLASSIMAGE_NO", sequenceName = "SEQ_CLASSIMAGE_NO", initialValue = 1, allocationSize = 1)
public class ClassImage {

  // 이미지 코드(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASSIMAGE_NO")
  private long no;
  
  // 파일명
  private String filename;
  
  // 파일크기
  private long filesize;
  
  // 실제데이터
  @Lob
  @ToString.Exclude
  private byte[] filedata;
  
  // 파일타입
  private String filetype;
  
  // 1:프로필이미지, 2:메인, 3:서브, 4:커리큘럼
  private int typechk;
  
  // 등록일자
  @UpdateTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private Date regdate;
  
  // 클래스 상품 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "classcode", referencedColumnName = "CLASSCODE")
  private ClassProduct classproduct;
}
