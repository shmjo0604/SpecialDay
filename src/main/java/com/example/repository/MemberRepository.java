package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Member;
// import com.example.entity.MemberProjection;

public interface MemberRepository extends JpaRepository<Member, String>{

    // select id, name, age from member1 order by id asc
    // public List<MemberProjection> findAllByOrderByIdAsc();
    
    // JPQL => select m.* from Member1 m order by m.name desc
    public List<Member> findAllByOrderByNameDesc();

    // JPQL => select m.* from Member1 m where m.name like '%?%' order by m.name desc
    public List<Member> findByNameContainingOrderByNameDesc(String name);

    public long countByNameContaining(String name);
    
}
