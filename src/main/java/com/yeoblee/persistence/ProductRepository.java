package com.yeoblee.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yeoblee.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Modifying
	@Transactional
	@Query("update Product p set p.pCnt = p.pCnt + 1 where p.prdNum = :prdNum")
	int updatePCnt(@Param("prdNum") Long prdNum);
	
//	@Modifying
//	@Transactional
//	@Query("update Works w set w.works_ref = w.seq, w.works_lev =:lev, w.works_seq = :_seq where w.seq = :seq")
//	void updateQnaLastSeq(@Param("lev") Long i, @Param("_seq") Long j, @Param("seq") Long seq);
	
	Page<Product> findBypNameContaining(String pName, Pageable pageable);
	Page<Product> findBypBrandContaining(String pBrand, Pageable pageable);
	Page<Product> findBypTypeContaining(String pType, Pageable pageable); 
	
}







