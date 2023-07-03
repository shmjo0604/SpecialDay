package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    
}
