package com.example.repository;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Review;
import com.example.entity.ReviewView;

@Transactional
@Repository
public interface ReviewViewRepository extends JpaRepository<ReviewView, Long> {

    List<ReviewView> findByIdIgnoreCaseOrderByRegdateDesc(String id, Pageable pageable);

    List<ReviewView> findById(String id);

    int countByIdContaining(String id);

    void save(Review obj);

    // native query 사용하기
    @Query(value = "SELECT * FROM ( SELECT r.*, ROW_NUMBER() OVER (ORDER BY regdate DESC) rown FROM REVIEWVIEW r WHERE r.id =:id) WHERE rown BETWEEN :start AND :end ORDER BY regdate DESC", nativeQuery = true)
    public List<ReviewView> selectReviewByIdPagenation(@Param("id") String id, @Param("start") int start,
            @Param("end") int end);

    // ReviewView 목록 조회 (by classcode)
    @Query(value = "SELECT * FROM ( SELECT r.*, ROW_NUMBER() OVER (ORDER BY r.no DESC) rown FROM REVIEWVIEW r WHERE r.classcode =:classcode ) r WHERE rown BETWEEN :first AND :last ORDER BY r.rown ASC", nativeQuery = true)
    public List<ReviewView> selectByClasscode(@Param("classcode") long classcode, @Param("first") int first, @Param("last") int last);

    // ReviewView 목록 개수 (by classcode)
    long countByClasscode(long classcode);

    // 4. 리뷰하나 조회(applyno에 해당하는 리뷰)
    public ReviewView findByIdAndNo(String id, long no);

    // 5. 리뷰 평균 값
    @Query(value = "SELECT ROUND( avg(rating),2)FROM REVIEWVIEW r WHERE id =:id", nativeQuery = true)
    public int avgReview(@Param("id") String id);

    // 6. 리뷰목록조회 (nativequery사용, 오래된순)
    @Query(value = "SELECT * FROM ( SELECT r.*, ROW_NUMBER() OVER (ORDER BY regdate ASC) rown FROM REVIEWVIEW r WHERE r.id =:id) WHERE rown BETWEEN :start AND :end ORDER BY regdate ASC", nativeQuery = true)
    public List<ReviewView> selectReviewByIdPagenationAsc(@Param("id") String id, @Param("start") int start,
                    @Param("end") int end);

    // 7. 리뷰 클릭시 조회수 증가
    @Modifying
    @Query(value ="UPDATE REVIEW r SET HIT=HIT+1 WHERE r.no=:no",nativeQuery = true)
    public int updateReviewHit(@Param("no") long no);


    interface ReviewViewVo {

        long getNo();

        int getRating();

        Date getRegdate();

        int getHit();

        String getId();

        long getClasscode();

        String getTitle();

        long getRown();

        Clob getContent();

    }
}
