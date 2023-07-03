package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Community;


@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findAllByOrderByNoDesc();

    Community findByNo(long no);

    List<Community> findByMember_id(String id);

    long countBy();

    long countByMember_id(String id);

    @Query(
        value="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY c.no DESC) rnum, c.* FROM COMMUNITY c) c WHERE rnum BETWEEN :first AND :last ORDER BY c.rnum ASC ", 
        nativeQuery = true)
    List<Community> selectOrderByNoDescPaging(@Param("first") int first, @Param("last") int last);

    @Query(
        value="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY c.no DESC) rnum, c.* FROM COMMUNITY c) c WHERE rnum BETWEEN 1 AND 6 ORDER BY c.rnum ASC ", 
        nativeQuery = true)
    List<Community> selectOrderByNoDescMain();

    // List<Community> findByTitleIgnoreCaseContainingOrderByNoDesc(String title);
    // List<Community> findByContentIgnoreCaseContainingOrderByNoDesc(String content);
    // List<Community> findByWriterIgnoreCaseContainingOrderByNoDesc(String writer);

    // //검색어+페이지네이션
    // List<Community> findByTitleIgnoreCaseContainingOrderByNoDesc(String title, Pageable pageable);
    // List<Community> findByContentIgnoreCaseContainingOrderByNoDesc(String content, Pageable pageable);
    // List<Community> findByWriterIgnoreCaseContainingOrderByNoDesc(String writer, Pageable pageable);
    
    // long countByTitleIgnoreCaseContainingOrderByNoDesc(String title);
    // long countByContentIgnoreCaseContainingOrderByNoDesc(String content);
    // long countByWriterIgnoreCaseContainingOrderByNoDesc(String writer);
    // // public Community updateCommuone(Community community);

    







   








   


   
    


}
