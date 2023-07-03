package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.ClassUnit;
import com.example.dto.ClassUnitView;

@Mapper
public interface ClassUnitMapper {
    
    /***** 판매자 *****/
	
	/* (1) ClassUnit 등록 */ 
	public int insertUnitOne(ClassUnit obj); // 사용X repositoy 대체
	
	/* (1-1) 클래스 가격 조회 => Unit 등록 시 추가 금액 등록을 위해 */
	public long selectPriceOne(long classcode); // 사용O
		
	/* (2) ClassUnit 전체 조회 */
	public List<ClassUnit> selectUnitList(long classcode); // 사용X repositoy 대체
	
	/* (2-1) ClassUnit 하나 조회 */
	public ClassUnit selectUnitOne(ClassUnit obj); 
	
	/* (3) ClassUnit 수정 */
	public int updateUnitOne(ClassUnit obj); 
	
	/* (4) ClassUnit 삭제 -> View 상에서만 삭제(DB에서는 삭제 불가) */ 
	public int updateUnitOneInactive(long no); 
	
	/* (4-1) ClassUnit 일괄 삭제 */
    public int updateUnitBatchInactive(Map<String, Object> map);

	/* (5) ClassUnitView 하나 조회 */
	public ClassUnitView selectClassUnitViewOne(long unitno);

	/* (6) ClassUnit 개설 일정 조회(calendar)*/
	public List<ClassUnit> selectUnitListToCal(long classcode);

	/* (7) ClassUnit 페이징 조회 (by classcode) */
	public List<ClassUnit> selectUnitListByClasscode(Map<String, Object> map);

	/* (8) ClassUnit 전체 개수 (by classcode) */
	public long selectUnitListCountByClasscode(long classcode);

	/* (9) ClassUnit 전체 조회 */
	public List<ClassUnit> selectClassUnitAll();

	/* (10) ClassUnit 완료 상태 변경(chk -> 2) */
	public int updateClassUnitComplete(long no);

	/* (11) 클래스 타이틀 조회 */ 
	public String selectClassProductTitleOne(long classcode);

	/* (12) ClassUnit 신청 인원 존재 유무 확인 (by memberid) */
	public int selectUnitViewCntCheck(String memberid);
}
