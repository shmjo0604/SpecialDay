package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.ActDetailCate;
import com.example.dto.ActivityCate;
import com.example.dto.CityCate;
import com.example.dto.ClassProduct;
import com.example.dto.ClassProductView;
import com.example.dto.ClassUnit;
import com.example.dto.ClassUnitView;
import com.example.dto.LocalCate;

@Mapper
public interface ClassSelectMapper {

    // 1-1. 클래스 조회(최신순) -> 날짜 있는 경우(pagination)
	public List<ClassUnitView> selectClassUnitViewList(Map<String, Object> map);

	// 1-2. 클래스 조회(최신순) -> 날짜 없는 경우(pagination)
	public List<ClassProduct> selectClassProductViewList(Map<String, Object> map);

	// 1-3. 클래스 조회(조회순) -> 날짜 있는 경우
	public List<ClassUnitView> selectClassUnitViewList2(Map<String, Object> map);

	// 1-4. 클래스 조회(조회순) -> 날짜 없는 경우
	public List<ClassProduct> selectClassProductViewList2(Map<String, Object> map);

	// 2-1. 클래스 조회 (총 개수 1-1)
	public long selectClassCountTotal(Map<String, Object> map);

	// 2-2. 클래스 조회 (총 개수 1-2)
	public long selectClassCountTotalV2(Map<String, Object> map);

	// 3. 지역 상위 카테고리 조회
	public List<CityCate> selectCityCateList();

	// 4. 지역 하위 카테고리 조회 -> 상위 카테고리 코드 외래키로 전달 받아서
	public List<LocalCate> selectLocalCateList(int citycode);

	// 5. 클래스 상위 카테고리 조회
	public List<ActivityCate> selectActivityCateList();

	// 6. 클래스 하위 카테고리 조회 -> 상위 카테고리 코드 외래키로 전달 받아서
	public List<ActDetailCate> selectActDetailCateList(int actcode);

	// 7. 클래스 하나 정보 조회
	public ClassProductView selectClassProductOne(long classcode);

	// 8. 클래스 유닛 목록 조회
	public List<ClassUnit> selectClassUnitList(ClassUnit obj);

	// 9. 클래스 유닛 하나 조회
	public ClassUnit selectClassUnitOne(long no);

}
