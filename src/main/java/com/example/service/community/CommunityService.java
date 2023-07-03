package com.example.service.community;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Community;
import com.example.entity.CommunityView;



@Service

public interface CommunityService {

  
    // 1. 커뮤니티 게시글 작성
    public int insertCommnuityOne(Community obj);

    // 2. 커뮤니티 게시글 조회 (paging)
    public List<CommunityView> selectCommunityList(long first, long last);

    // 3. 커뮤니티 게시글 1개 조회
    public Community selectCommunityOne(long no);
    
    // 4. 커뮤니티 게시글 전채 개수 조회
    public long countCommunityList();

    // 5. 게시글 조회수 증가
    public int updatehit(long no);

    // 6. 홈 화면 커뮤니티 최신글 조회
    public List<Community>  selectOrderByNoDescMain();

 

}
