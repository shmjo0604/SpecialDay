package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    
}
