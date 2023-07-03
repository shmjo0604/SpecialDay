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
@Table(name = "CITYCATE")
@Entity
@SequenceGenerator(name = "SEQ_CITYCATE_NO", sequenceName = "SEQ_CITYCATE_NO", initialValue = 1, allocationSize = 1)
public class CityCate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITYCATE_NO")
    private int code;

    // 지역(서울,경기,부산,인천,대구,울산,광주,대전,경상남도,경상북도,전라남도,전라북도,충청남도,충청북도,강원도,제주도,세종)
    @Column(nullable = false)
    private String cate;

    // 상세지역카테고리테이블
    @ToString.Exclude
    @OneToMany(mappedBy = "citycate", fetch = FetchType.LAZY)
    private List<LocalCate> localcateList = new ArrayList<>();
}
