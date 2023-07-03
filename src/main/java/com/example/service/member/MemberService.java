package com.example.service.member;

import org.springframework.stereotype.Service;

import com.example.dto.Member;

@Service
public interface MemberService {

    // 1. 회원가입
    public int insertMemberOne(Member obj);

    // 2. 아이디 중복 확인
    public int selectMemberIDCheck(String id);

    // 3. 로그인
    public Member selectMemberLogin(Member obj);

    // 4. 회원 정보 조회
    public Member selectMemberOne(String id);

    // 5. 아이디 찾기
    public Member selectFindMemberId(Member obj);

    // 6. 회원 정보 수정
    public int updateMemberOne(Member obj);

    // 7. 비밀번호 변경
    public int updateMemberPassword(Member obj);

    // 8. 회원 탈퇴
    public int deleteMemberOne(Member obj);
    
}
