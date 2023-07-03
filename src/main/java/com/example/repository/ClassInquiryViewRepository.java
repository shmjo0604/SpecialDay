package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.ClassInquiryView;
import com.example.entity.ClassInquiryViewVo;

@Repository
public interface ClassInquiryViewRepository extends JpaRepository<ClassInquiryView, Long>{
  
    ClassInquiryView findByNo(long no);
    
    // 2. 문의 총 개수 (by 클래스 판매자 아이디)

    long countByOwner(String owner);

    List<ClassInquiryView> findByOwnerOrderByNoDesc(String owner, Pageable pagealbe);

    // 3. 문의 목록 (by 클래스 판매자 아이디 + pagination)

    @Query(
        value="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY c.no DESC) rnum, c.* FROM CLASSINQUIRYVIEW c WHERE c.owner = :id ) c WHERE rnum BETWEEN :first AND :last ORDER BY c.rnum ASC ", 
        nativeQuery = true) // :name (nativequery) = #{name} (mybatis)
    List<ClassInquiryViewVo> selectByOwnerOrderByNoDescPaging(@Param("id") String owner, @Param("first") int first, @Param("last") int last);

    // 4. 문의 목록 (by 사용자 아이디 + pagination)

    @Query(
        value="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY c.no DESC) rnum, c.* FROM CLASSINQUIRYVIEW c WHERE c.memberid = :id ) c WHERE rnum BETWEEN :first AND :last ORDER BY c.rnum ASC ", 
        nativeQuery = true)
    List<ClassInquiryViewVo> selectClassInquiryListByMemberid(@Param("id") String id, @Param("first") int first, @Param("last") int last);


    @Query(
        value="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY c.no DESC) rnum, c.* FROM CLASSINQUIRYVIEW c WHERE c.memberid = :id AND c.chk = :chk) c WHERE rnum BETWEEN :first AND :last ORDER BY c.rnum ASC ", 
        nativeQuery = true)
    List<ClassInquiryViewVo> selectByMemberidAndChk(@Param("id") String id, @Param("chk") int chk, @Param("first") int first, @Param("last") int last);

    // 5. 문의 총 개수 (by 클래스 판매자 아이디)

    long countByOwnerAndChk(String owner, int chk);

    // 6. 문의 목록 (by 클래스 판매자 아이디 + pagination)

    @Query(
        value="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY c.no DESC) rnum, c.* FROM CLASSINQUIRYVIEW c WHERE c.owner = :id AND c.chk = :chk) c WHERE rnum BETWEEN :first AND :last ORDER BY c.rnum ASC ", 
        nativeQuery = true) // :name (nativequery) = #{name} (mybatis)
    List<ClassInquiryViewVo> selectByOwnerANDChkOrderByNoDescPaging(@Param("id") String owner, @Param("first") int first, @Param("last") int last, @Param("chk")int chk);
}
