package com.yeoblee.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yeoblee.domain.QnaComment;

public interface QnaCommentRepository extends JpaRepository<QnaComment, Long>{

}
