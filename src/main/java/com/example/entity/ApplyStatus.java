package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "APPLYSTATUS")
@Entity
@SequenceGenerator(name = "SEQ_APPLYSTATUS_NO", sequenceName = "SEQ_APPLYSTATUS_NO", initialValue = 1, allocationSize = 1)
public class ApplyStatus {

  // 신청 상태 번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLYSTATUS_NO")
  private long no;

  // 신청 처리상태(1:결제완료, 2:결제취소, 3:참여완료)
  private int chk;
  
  // 상태 등록일자
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(name = "REGDATE", insertable = true, updatable = false)
  private Date regdate;
  
  // 신청 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "APPLYNO", referencedColumnName = "NO")
  private Apply apply;
}
