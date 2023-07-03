package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ClassAnswer;

@Repository
public interface ClassAnswerRepository extends JpaRepository<ClassAnswer, Long> {

    int countByNo(long no);
    
}
