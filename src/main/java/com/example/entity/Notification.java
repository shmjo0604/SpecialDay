package com.example.entity;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name = "NOTIFICATION")
@Entity
@SequenceGenerator(name = "SEQ_NOTIFICATION_NO", sequenceName = "SEQ_NOTIFICATION_NO", initialValue = 1, allocationSize = 1)
public class Notification {

  // 알림번호(시퀀스)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTIFICATION_NO")
  private long no;

  // 알림내용
  private String content;

  // 알림분류
  private String type;

  // 알림이동주소
  private String url;

  // 확인상태(0,1)
  private int chk;

  // 등록 날짜
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private Date regdate;

  // 회원 테이블
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "receiver", referencedColumnName = "ID")
  private Member member;

}

