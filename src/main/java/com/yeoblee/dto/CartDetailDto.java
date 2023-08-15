package com.yeoblee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetailDto {
	
	private Long cartItemId; //장바구니 상품 아이디

    private String itemName; //상품명

    private Long price; //상품 금액

    private int count; //수량

    private String imgUrl; //상품 이미지 경로
    

    public CartDetailDto(Long cartItemId, String itemName, Long price, int count, String imgUrl){
    	
        this.cartItemId = cartItemId;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
        
    }

}
