package com.example.service.apply;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.Apply;
import com.example.dto.ApplyStatus;
import com.example.dto.ApplyStatusView;
import com.example.dto.ApplyView;

@Service
public interface ApplyService {

    // 1. 클래스 신청 (chk -> (default) 1)
    public int insertApplyBatch(List<Apply> list);

    // 2. 클래스 신청 -> 기록
    public int insertApplyStatusBatch(List<ApplyStatus> list);

    // 3. 클래스 신청 취소 (chk -> 2)
    public int updateApplyCancel(Apply obj);

    // 4. 클래스 참여 완료 (chk -> 3)
    public int updateApplyComplete(List<Apply> list);

    // 5. 클래스 신청 시, 인원수 변경
    public int updateClassUnitApplySuccess(List<Apply> list);

    // 6. 클래스 신청 취소 시, 인원수 변경
    public int updateClassUnitApplyCancel(Apply obj);

    // 7. 클래스 신청 내역 조회
    public List<ApplyView> selectApplyListById(Map<String, Object> map);

    // 8. 신청 전체 개수 조회
    public long countApplyList(String id);

    // 9. 신청 전체 개수 조회(chk=1)
    public long countApplyListOne(String id);

    // 10. 신청 전체 개수 조회(chk=2)
    public long countApplyListTwo(String id);

    // 11. 신청 전체 개수 조회(chk=3)
    public long countApplyListThree(String id);

    // 12. 클래스 신청 내역 조회(no,id)
    public List<ApplyStatusView> selectApplyStatusListById(Map<String, Object> map);

    // 13. 클래스 신청 내역 조회(chk=1 결제완료만)
    public List<ApplyView> selectApplyListByIdOne(Map<String, Object> map);

    // 14. 클래스 신청 내역 조회(chk=2 결제취소만)
    public List<ApplyView> selectApplyListByIdTwo(Map<String, Object> map);

    // 15. 클래스 신청 내역 조회(chk=3 참여완료만)
    public List<ApplyView> selectApplyListByIdThree(Map<String, Object> map);

    // ************************************* 신청관리  ***********************************
    // 16. 클래스별 신청내역 조회 
    public List<ApplyView> selectApplyViewListByUnitno(long unitno);

    // 17. 신청상태 수정(결제완료 : 1 => 참여완료 : 3)
    public int updateApplyChk(long no);

    // 18. 신청 상태 기록 - 신청 상태 테이블
    public int insertApplyStatusOne(long applyno, int chk);

}
