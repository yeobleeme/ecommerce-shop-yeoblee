package com.yeoblee.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yeoblee.domain.Qna;

public interface QnaRepository extends JpaRepository<Qna, Long>{
	
	@Modifying
	@Transactional
	@Query("update Qna q set q.qnaCnt = q.qbaCnt + 1 where q.qnaNum= = :qnaNum")
	int updateQnaCnt(@Param("qnaNum") Long qnaNum);
	
//	@Modifying
//	@Transactional
//	@Query("update Works w set w.works_ref = w.seq, w.works_lev =:lev, w.works_seq = :_seq where w.seq = :seq")
//	void updateQnaLastSeq(@Param("lev") Long i, @Param("_seq") Long j, @Param("seq") Long seq);
	
	Page<Qna> findByQnaTitleContaining(String title, Pageable pageable);
	Page<Qna> findByQnaWriterContaining(String writer, Pageable pageable);
	Page<Qna> findByQnaContentContaining(String content, Pageable pageable); 

}
