package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "BASKETVIEW")
public class BasketView {

    @Id
	@Column(name = "NO") 
	private  long  no;

    @Column(name = "CNT") 
    private int cnt;

    @Column(name = "MEMBERID")
    private String memberid = null;

    @Column(name = "UNITNO") 
    private long unitno;

    @Column(name = "CLASSCODE") 
    private long classcode;

    @Column(name = "MEMBERNAME")
    private String membername = null;

    @Column(name = "TITLE")
    private String title = null;

    @Column(name = "CLASSDATE")
    private String classdate = null;

    @Column(name = "CLASSSTART")
    private String classstart = null;

    @Column(name = "CLASSEND")
    private String classend = null;

    @Column(name = "CLASSLEVEL") //level이라 해야하는 데 실수로 lavel로 씀 나중에 고쳐야 함
    private int classlevel;

    @Column(name = "TOTALPRICE")
    private int totalprice;

    @Transient
    private long mainImg;

    private int maximum;



    


    
}
