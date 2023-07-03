package com.example.service.reply;

import org.springframework.stereotype.Service;

import com.example.entity.Reply;

@Service
public interface ReplyService {
    
    //커뮤니티 댓글 등록
    public int insertReplyOne(Reply obj);

}
