package com.yeoblee.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yeoblee.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findByMemberMbrNum(Long mbrNum);

}
