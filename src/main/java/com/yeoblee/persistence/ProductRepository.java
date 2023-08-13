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
	@Query("update Product p set p.prdCnt = p.prdCnt + 1 where p.prdNum = :prdNum")
	int updatePrdCnt(@Param("prdNum") Long prdNum);
	
//	@Modifying
//	@Transactional
//	@Query("update Works w set w.works_ref = w.seq, w.works_lev =:lev, w.works_seq = :_seq where w.seq = :seq")
//	void updateQnaLastSeq(@Param("lev") Long i, @Param("_seq") Long j, @Param("seq") Long seq);
	
	Page<Product> findByPrdNameContaining(String pName, Pageable pageable);
	Page<Product> findByPrdBrandContaining(String pBrand, Pageable pageable);
	Page<Product> findByPrdTypeContaining(String pType, Pageable pageable); 
	
}







