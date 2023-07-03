package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ClassImage;

@Repository
public interface ClassImageRepository extends JpaRepository<ClassImage, Long> {
    
}
