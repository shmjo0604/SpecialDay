package com.example.service.classproduct;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.ClassInquiry;
import com.example.entity.ClassInquiryView;
import com.example.entity.ClassInquiryViewVo;

@Service
public interface ClassInquiryService {

    // 1. 클래스 문의 등록
    public int insertClassInquiryOne(ClassInquiry obj);

    // 2. 클래스 문의 조회 (by classcode + paging)
    public List<ClassInquiry> selectClassInquiryList(long classcode);

    // 3. 클래스 문의 조회 총 개수 (by classcode)
    public long selectCountClassInquiryList(long classcode);

    // 4. 클래스 문의 하나 조회
    public ClassInquiryView selectClassInquiryViewOne(long no);

    // 5. 클래스 문의 조회 (by memberid + paging)
    public List<ClassInquiryViewVo> selectClassInquiryListByMemberid(String id, int first, int last);

    // 6. 클래스 문의 조회 총 개수 (by memberid)
    public long selectClassInquiryCountByMemberid(String id);

    // 7. 클래스 문의 조회 개수 (by memberid + chk)
    public long selectClassInquiryCountByidAndChk(String id, int chk);

    // 8. 클래스 문의 조회 (by memberid and chk + paging)
    public List<ClassInquiryViewVo> selectByMemberidAndChk(String id, int chk, int first, int last);


}
