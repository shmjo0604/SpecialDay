package com.example.service.classproduct;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.ActDetailCate;
import com.example.dto.ActivityCate;
import com.example.dto.CityCate;
import com.example.dto.ClassProduct;
import com.example.dto.ClassProductView;
import com.example.dto.ClassUnit;
import com.example.dto.ClassUnitView;
import com.example.dto.LocalCate;

@Service
public interface ClassSelectService {

    // 1-1. 클래스 조회 (최신순, 날짜 있는 경우)
    public List<ClassUnitView> selectClassUnitViewList(Map<String, Object> map);

    // 1-2. 클래스 조회 (최신순, 날짜 없는 경우)
    public List<ClassProduct> selectClassProductViewList(Map<String, Object> map);

    // 1-3. 클래스 조회(조회순, 날짜 O)
    public List<ClassUnitView> selectClassUnitViewList2(Map<String, Object> map);

    // 1-4. 클래스 조회(조회순, 날짜 X)
    public List<ClassProduct> selectClassProductViewList2(Map<String, Object> map);

    // 2-1. 클래스 조회 (총 개수 1-1)
    public long selectClassCountTotal(Map<String, Object> map);
    
    // 2-2. 클래스 조회 (총 개수 1-2)
    public long selectClassCountTotalV2(Map<String, Object> map);

    // 3. 지역 상위 분류 호출
    public List<CityCate> selectCityCateList();

    // 4. 클래스 상위 분류 호출
    public List<ActivityCate> selectActivityCateList();

    // 5. 지역 하위 분류 호출
    public List<LocalCate> selectLocalCateList(int citycode);

    // 6. 클래스 하위 분류 호출
    public List<ActDetailCate> selectActDetailCateList(int actcode);

    // 7. 클래스 하나 정보 조회
	public ClassProductView selectClassProductOne(long classcode);

    // 8. 클래스 유닛 목록 조회
	public List<ClassUnit> selectClassUnitList(ClassUnit obj);

    // 9. 클래스 유닛 하나 조회
    public ClassUnit selectClassUnitOne(long no);

    // 10. 클래스 조회수 증가
    public int updateClassProductHit(com.example.entity.ClassProduct obj);

    // 11. 홈 신규 클래스 조회
    public List<com.example.entity.ClassProduct> selectMainHomeClassList();

}
