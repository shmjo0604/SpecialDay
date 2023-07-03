package com.example.service.basket;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.entity.Basket;
import com.example.entity.BasketView;

@Service
public interface BasketService {

    // 1. 장바구니 추가
    public int insertBasketOne(Basket obj);

    // 2. 장바구니 조회
    public List<BasketView> basketList(String memberid);

    // 3. 상품 하나 삭제
    public int deleteBasketOne(Basket obj);

    // 4. 선택 상품 삭제
    public int deleteBakset(List<Long> list);

    // 5. 전체 장바구니 조회
    public List<Basket> baksetAll();

    

    
}
