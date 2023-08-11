package com.yeoblee.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cart_item")
public class CartItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ciNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cNum")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pNum")
    private Product product;

    private int ciQty;
    

    public static CartItem createCartItem(Cart cart, Product product, int ciQty) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setCiQty(ciQty);
        return cartItem;
    }

    
    public void addCount(int count) {
        this.ciQty += count;
    }
	

}




