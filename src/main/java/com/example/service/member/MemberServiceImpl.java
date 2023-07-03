package com.example.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Member;
import com.example.entity.ClassProduct;
import com.example.entity.Community;
import com.example.mapper.MemberMapper;
import com.example.repository.ClassProductRepository;
import com.example.repository.CommunityRepository;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired MemberMapper mMapper;
    @Autowired ClassProductRepository classProductRepository;
    @Autowired CommunityRepository communityRepository;

    @Override
    public int insertMemberOne(Member obj) {
        try {
            return mMapper.insertMemberOne(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        } 
    }

    @Override
    public int selectMemberIDCheck(String id) {
        try {
            return mMapper.selectMemberIDCheck(id);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Member selectMemberLogin(Member obj) {
        try {
            return mMapper.selectMemberLogin(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Member selectMemberOne(String id) {
        try {
            return mMapper.selectMemberOne(id);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Member selectFindMemberId(Member obj) {
        try {
            return mMapper.selectFindMemberId(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateMemberOne(Member obj) {
        try {
            return mMapper.updateMemberOne(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateMemberPassword(Member obj) {
        try {
            return mMapper.updateMemberPassword(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteMemberOne(Member obj) {
        try {

            int result1 = classProductRepository.countByMember_id(obj.getId());
            long result2 = communityRepository.countByMember_id(obj.getId());

            System.out.println("클래스 개수 : " + result1);
            System.out.println("게시글 개수 : " + result2);

            // 1. 클래스가 있다면, 등록된 클래스를 모두 삭제(update)

            if(result1 > 0) {

                List<ClassProduct> list = classProductRepository.findByMember_idAndChk(obj.getId(), 1);

                for(ClassProduct classProduct : list) {

                    classProduct.setChk(2);

                }

                classProductRepository.saveAll(list);

            }

            // 2. 게시글 삭제(delete)

            if(result2 > 0) {

                List<Community> list = communityRepository.findByMember_id(obj.getId());

                communityRepository.deleteAll(list);

            }

            return mMapper.deleteMemberOne(obj);
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
