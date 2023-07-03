package com.example.service.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Reply;
import com.example.repository.ReplyRepository;


@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired 
    public ReplyRepository rRepository;
    public ReplyService rService;

    @Override
    public int insertReplyOne(Reply obj) {
        try {
            rRepository.save(obj);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    
}
