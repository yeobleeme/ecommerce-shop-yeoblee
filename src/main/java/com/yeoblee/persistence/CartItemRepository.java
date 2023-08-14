package com.yeoblee.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yeoblee.domain.CartItem;
import com.yeoblee.dto.CartDetailDto;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	// 카트 아이디와 아이템 아이디를 이용하여 카트 아이템 레퍼지토리의 엔티티 조회
    CartItem findByCartIdAndProductPrdNum(Long cartId, Long prdNum);

 // 장바구니 페이지에 전달할 CartDetailDto 를 쿼리로 조회해서 CartDetailDtoList 에 담아줌
//    @Query("SELECT new com.yeoblee.dto.CartDetailDto(ci.ciNum, p.pName, p.pPrice, ci.ciQty, pi.pDtImage) " +
//            "FROM CartItem ci " +
//            "JOIN ci.product p " +
//            "JOIN p.pDtImages pi " +
//            "WHERE ci.cart.cNum = :cartId " +
//            "AND pi.pDtImage = 'Y' " + // 장바구니에 담겨있는 상품의 대표 이미지만 가져옴
//            "ORDER BY ci.regTime DESC")
//    
//    List<CartDetailDto> findCartDetailDtoList(Long cartId);
    
//    @Query("SELECT new com.yeoblee.dto.CartDetailDto(ci.ciNum, p.pName, p.pPrice, ci.ciQty, pi) " +
//            "FROM CartItem ci " +
//            "JOIN ci.product p " +
//            "JOIN p.pDtImages pi " +
//            "WHERE ci.cart = :cart " +
//            "AND pi = 'Y' " + // 장바구니에 담겨있는 상품의 대표 이미지만 가져옴
//            "ORDER BY ci.regTime DESC")
//    List<CartDetailDto> findCartDetailDtoList(@Param("cart") Cart cart);
    
//    // 장바구니 페이지에 전달할 CartDetailDto 를 쿼리로 조회해서 CartDetailDtoList 에 담아줌
//    @Query("select new kr.co.codewiki.shoppingmall.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
//            "from CartItem ci, ItemImg im " +
//            "join ci.item i " +
//            "where ci.cart.id = :cartId " +
//            "and im.item.id = ci.item.id " +
//            "and im.repimgYn = 'Y' " + // 장바구니에 담겨있는 상품의 대표 이미지만 가져옴
//            "order by ci.regTime desc"
//    )
//    List<CartDetailDto> findCartDetailDtoList(Long cartId);
    
    @Query("SELECT new com.yeoblee.dto.CartDetailDto(ci.id, prd.prdName, prd.prdPrice, ci.count, prd.prdThImage) " +
            "FROM CartItem ci " +
            "JOIN ci.product prd " +
            "WHERE ci.cart.id = :cartId")
    List<CartDetailDto> findCartDetailDtoList(Long cartId);

}
