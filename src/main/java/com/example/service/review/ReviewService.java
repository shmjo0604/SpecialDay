package com.example.service.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Review;
import com.example.entity.ReviewImage;
import com.example.entity.ReviewImageProjection;
import com.example.entity.ReviewView;

@Service
public interface ReviewService {

    // 1. 리뷰 등록
    public int insertReview(Review obj);

    // 2. 리뷰 목록 조회(by memberid)
    public List<ReviewView> selectlistReviewview(String id);

    // 3. 리뷰 개수 카운트
    public int countReview(String id);

    // 4. 네이티브쿼리 리뷰 목록 조회(by memberid, 최신순)
    public List<ReviewView> selectReviewByIdPagenation(String id, int start, int end);

    // 5. 리뷰 이미지 등록 
    public int insertReviewImage(List<ReviewImage> obj);

    // 6. 리뷰 목록 조회(by classcode)
    public List<ReviewView> selectByClasscode(long classcode, int first, int last);

    // 7. 리뷰 목록 개수(by classcode)
    public long countByClasscode(long classcode);

    // 8. 리뷰 이미지 조회(by reviewno)
    public List<ReviewImageProjection> selectReviewImageNoList(long reviewno);

    // 9. 리뷰 이미지 데이터 조회(by no)
    public ReviewImage selectReivewImageOne(long no);

    // 10. 리뷰 하나만 조회
    public ReviewView selectReviewOne(String id, long no);

    // 11. 리뷰 전체 평균값
    public int avgReview(String id);

    // 12. 네이티브쿼리 리뷰목록 조회(오래된순)
    public List<ReviewView> selectReviewByIdPagenationAsc(String id, int start, int end);

    // 13. 리뷰 이미지 조회
    public ReviewImage selectReviewImage(long no);

    // 14. 리뷰이미지 번호 조회
    public List<Long> reviewImagelistNo(long no);

     // 15. 리뷰 클릭시 조회수 증가
    public int updateReviewHit(long no);
}
