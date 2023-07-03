package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Table(name = "CLASSANSWER")
@Entity
public class ClassAnswer {

  @Id
  private long no;

  // 답변제목
  private String title;

  // 답변내용
  @Lob
  private String content;

  // 답변날짜
  @CreationTimestamp
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @Column(insertable = true, updatable = false)
  private Date regdate;

  // 클래스 문의
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @JoinColumn(name = "no", referencedColumnName = "NO")
  @MapsId
  private ClassInquiry classinquiry;
}
