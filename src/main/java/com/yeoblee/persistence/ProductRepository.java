package com.yeoblee.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yeoblee.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
//	@Modifying
//	@Transactional
//	@Query("update Product p set p.hit = p.hit + 1 where p.p_num = :p_num")
//	int updateReadCount(@Param("p_num") Long p_num);
//	
//	@Modifying
//	@Transactional
//	@Query("update Product p set p.p_ref = p.p_num, p.p_lev =:lev, p.p_seq =:_seq where p.p_num = :seq")
//	void updateLastSeq(@Param("lev") Long i, @Param("_p_num") Long j, @Param("p_num") Long p_num);
//	
//	Page<Works> findByTitleContaining(String title, Pageable pageable);
//	Page<Works> findByWriterContaining(String writer, Pageable pageable);
//	Page<Works> findByContentContaining(String content, Pageable pageable); 

}







