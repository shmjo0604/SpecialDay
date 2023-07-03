package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
    List<Reply>  findByCommunity_noOrderByNoDesc(long no);

    int countByCommunity_no(long communityno);
}
