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
@Table(name = "PROOFIMAGE")
@Entity
@SequenceGenerator(name = "SEQ_CLASSAPPROVAL_NO", sequenceName = "SEQ_CLASSAPPROVAL_NO", initialValue = 1, allocationSize = 1)
public class ProofImage {

  // 클래스 코드(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASSAPPROVAL_NO")
  private long no;

  // 파일이름
  private String filename;

  // 파일사이즈
  private long filesize;

  // 파일데이터
  @Lob
  @ToString.Exclude
  private byte[] filedata;

  // 파일타입
  private String filetype;

  // 등록일자
  @UpdateTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private Date regdate;

  // 클래스 상품 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CLASSCODE", referencedColumnName = "CLASSCODE")
  private ClassProduct classproduct;
}
