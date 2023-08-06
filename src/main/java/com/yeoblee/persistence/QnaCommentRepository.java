package com.yeoblee.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yeoblee.domain.QnaComment;

public interface QnaCommentRepository extends JpaRepository<QnaComment, Long>{
	
	@Query("select qc from QnaComment qc where qc.qna.qnaNum = :qnaNum")
	List<QnaComment> findByQnaNum(@Param("qnaNum") Long qnaNum);

}
