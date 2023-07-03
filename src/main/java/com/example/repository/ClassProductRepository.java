package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.ClassProduct;

@Repository
public interface ClassProductRepository extends JpaRepository<ClassProduct, Long> {
    
    @Query(
        value="SELECT * FROM (SELECT c.*, ROW_NUMBER() OVER(ORDER BY c.classcode DESC) rnum FROM CLASSPRODUCT c WHERE c.chk = 1) c WHERE rnum BETWEEN 1 AND 6 ORDER BY c.rnum ASC ", 
        nativeQuery = true)
    List<ClassProduct> findAllTopSixByOrderByClasscodeDesc();

    int countByMember_id(String id);

    List<ClassProduct> findByMember_idAndChk(String id, int chk);

}
