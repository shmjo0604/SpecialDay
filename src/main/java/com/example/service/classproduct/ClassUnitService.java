package com.example.service.classproduct;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.ClassUnit;
import com.example.dto.ClassUnitView;

@Service
public interface ClassUnitService {
    
    /* (1) ClassUnit 등록*/
	public int insertUnitOne(com.example.entity.ClassUnit obj);
	
	/* (1-1) 클래스 가격 조회 => Unit 등록 시 추가 금액 등록을 위해 */
	public long selectPriceOne(long classcode); 
		
	/* (2) ClassUnit 전체 조회 */
	public List<com.example.entity.ClassUnit> selectUnitList(long classcode);
	
	/* (2-1) ClassUnit 하나 조회 */
	public com.example.entity.ClassUnit selectUnitOne(long classcode, long no);
	
	/* (3) ClassUnit 수정 */
	public int updateUnitOne(long classcode, long no);
	
	/* (4) ClassUnit 삭제 -> View 상에서만 삭제(DB에서는 삭제 불가) */
	public int updateUnitOneInactive(long classcode, long no);
	
	/* (5) ClassUnit 전체 삭제 */
    public int updateUnitAllInactive(long classcode);

	/* (6) ClassUnit 개설 일정 조회(calendar) */
	public List<ClassUnit> selectUnitListToCal(long classcode);
	
	/* (7) ClassUnitView 하나 조회 */
	public ClassUnitView selectClassUnitViewOne(long unitno);

	/* (8) ClassUnit 페이징 조회 (by classcode) */
	public List<ClassUnit> selectUnitListByClasscode(Map<String, Object> map);

	/* (9) ClassUnit 전체 개수 (by classcode) */
	public long selectUnitListCountByClasscode(long classcode);

	/* (10) 클래스 타이틀 조회 */ 
	public String selectClassProductTitleOne(long classcode);

	/* (10) ClassUnit 신청 인원 존재 유무 확인 (by memberid) */
	public int selectUnitViewCntCheck(String memberid);

}
