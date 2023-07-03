package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.BasketView;

@Repository
public interface BasketViewRepository extends JpaRepository<BasketView, Long> {
    
    List<BasketView> findByMemberidOrderByNoDesc(String id);

}
