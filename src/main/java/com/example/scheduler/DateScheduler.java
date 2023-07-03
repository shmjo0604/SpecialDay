package com.example.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dto.ClassUnit;
import com.example.entity.Basket;
import com.example.mapper.ClassUnitMapper;
import com.example.repository.BasketRepository;
import com.example.repository.ClassUnitRepository;
import com.example.service.basket.BasketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateScheduler {

    final String format = "DateScheduler => {}";

    @Autowired ClassUnitMapper unitMapper;
    @Autowired BasketRepository basketRepository;
    @Autowired ClassUnitRepository unitRepository;

    @Autowired BasketService basketService;

    // 1. ClassUnit 완료 상태 변경 & 해당 상태에 따라 장바구니에서도 해당 ClassUnit 삭제(매일 자정에 실행)

    @Scheduled(cron = "0 0 0 * * *")
    // @Scheduled(cron = "*/10 * * * * *")
    // @Transactional
    public void ClassUnitComplete() throws Exception {

        // (1). chk = 1 (실행 이전 상태 ClassUnit 모두 조회)
        List<ClassUnit> list = unitMapper.selectClassUnitAll();

        if (!list.isEmpty()) {

            // (2). 오늘 날짜 호출
            Date today = new Date();

            for (ClassUnit obj : list) {

                // (3). ClassUnit => classdate 컬럼(타입 String)을 Date 타입으로 변경
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date date = formatter.parse(obj.getClassdate());

                // (4). 해당 날짜가 오늘 날짜보다 이전이라면 => 해당 객체 chk 값을 2로 변경
                if (today.after(date)) {

                    int ret = unitMapper.updateClassUnitComplete(obj.getNo());
                    // System.out.println(ret);
                }

            }
        }

        // (5). 장바구니 전체 목록 호출
        List<Basket> baskets = basketRepository.findAll();

        if(!baskets.isEmpty()) {
            for(Basket obj : baskets) {

                // (6). Basket 삭제(chk = 2인 classunit을 담은 경우)
                long no = obj.getClassunit().getNo();
    
                if(unitRepository.findByNo(no).getChk() == 2) {
    
                    basketRepository.delete(obj);
    
                }
    
            }
        }
        
        // log.info(format, baskets.toString());

    }

    // 2. Basket 30일 이후 삭제 (매일 자정에 실행)
    @Scheduled(cron = "0 0 0 * * *")
    // @Scheduled(cron = "*/5 * * * * *")
    // @Transactional
    public void BasketExpiration() throws Exception {

        // (1). 장바구니 전체 목록 호출
        List<Basket> baskets = basketRepository.findAll();

        Date today = new Date();

        if(!baskets.isEmpty()) {
            for(Basket obj : baskets) {

                // (2). 등록일자로부터 30일이 지났는지 확인

                long today_time = today.getTime();
                long regdate_time = obj.getRegdate().getTime();
                
                // log.info(format, (today_time - regdate_time)/1000/60/60/24);

                // (3). Basket 삭제
                
                if(((today_time - regdate_time)/1000/60/60/24) > 30) {
                    
                    basketRepository.delete(obj);

                }

            }
        }

    }

}
