package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.ActDetailCate;
import com.example.dto.ActivityCate;
import com.example.dto.CityCate;
import com.example.dto.ClassImage;
import com.example.dto.ClassProduct;
import com.example.dto.LocalCate;


@Mapper
public interface ClassInsertMapper {

    /* 클래스 관리 - (1) 클래스 등록 관련 기능 */    

    // 1. 클래스 등록
    public int insertClassOne(ClassProduct obj);

    // 2. 지역 상위 카테고리 조회
    public List<CityCate> selectCityCateList();

    // 3. 지역 하위 카테고리 조회 -> 상위 카테고리 코드 외래키로 전달 받아서
    public List<LocalCate> selectLocalCateList(int citycode);

    // 4. 클래스 상위 카테고리 조회
    public List<ActivityCate> selectActivityCateList();

    // 5. 클래스 하위 카테고리 조회 -> 상위 카테고리 코드 외래키로 전달 받아서
    public List<ActDetailCate> selectActDetailCateList(int actcode);

    // 6. 클래스 이미지 일괄 등록
    public int insertClassImage(List<ClassImage> list);

    // 7. 가장 최근에 등록한 클래스 코드 조회
    public long selectClasscodeRecent(String id);

}
