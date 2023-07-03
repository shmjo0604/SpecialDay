package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CommunityView;

@Repository
public interface CommunityViewRepository extends JpaRepository<CommunityView, Long> {
    
    List<CommunityView> findByRnumBetweenOrderByRnumAsc(long first, long last);

}
