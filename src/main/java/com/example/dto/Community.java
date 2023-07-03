package com.example.dto;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "COMMUNITY")
public class Community {
      // 글 조회수
  private int hit  = 1;
}
