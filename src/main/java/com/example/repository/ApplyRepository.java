package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Apply;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
    
}
