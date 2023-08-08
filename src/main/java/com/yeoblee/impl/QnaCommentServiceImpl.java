package com.yeoblee.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeoblee.domain.QnaComment;
import com.yeoblee.persistence.QnaCommentRepository;
import com.yeoblee.service.QnaCommentService;

@Service
public class QnaCommentServiceImpl implements QnaCommentService {
	
	@Autowired
	private QnaCommentRepository qnaCommentRepository;

	@Override
	public QnaComment getQnaComment(QnaComment qnaComment) {
		
		Optional<QnaComment> findQnaComment = qnaCommentRepository.findById(qnaComment.getQcNum());
		if(findQnaComment.isPresent())
			return findQnaComment.get();
		else return null;
		
	}
	
	@Override
	public List<QnaComment> getQnaCommentList() {
		return (List<QnaComment>) qnaCommentRepository.findAll();
	}
	

	@Override
	public void insertQnaComment(QnaComment qnaComment) {
		qnaCommentRepository.save(qnaComment);		
	}

	@Override
	public void updateQnaComment(QnaComment qnaComment) {
		qnaCommentRepository.save(qnaComment);		
	}

	@Override
	public void deleteQnaComment(QnaComment qnaComment) {
		qnaCommentRepository.deleteById(qnaComment.getQcNum());		
	}

}