package com.example.service.basket;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Basket;
import com.example.entity.BasketView;
import com.example.repository.BasketRepository;
import com.example.repository.BasketViewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    final BasketRepository bRepository;
    final BasketViewRepository bViewRepository;

    // 1. 장바구니 추가
    @Override
    public int insertBasketOne(Basket obj) {

        try {
            bRepository.save(obj);
            return 1;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 2. 장바구니 조회
    @Override
    public List<BasketView> basketList(String memberid) {
        try {
            return bViewRepository.findByMemberidOrderByNoDesc(memberid);
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 3. 장바구니 상품 하나 삭제
    @Override
    public int deleteBasketOne(Basket obj) {

        try {
            bRepository.deleteById(obj.getNo());
            return 1;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public int deleteBakset(List<Long> list) {
        try {
            bRepository.deleteAllById(list);
            return 1;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Basket> baksetAll() {
        try {
            return bRepository.findAll();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
