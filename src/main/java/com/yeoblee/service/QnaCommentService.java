package com.yeoblee.service;

import java.util.List;

import com.yeoblee.domain.QnaComment;

public interface QnaCommentService {
	
	QnaComment getQnaComment(QnaComment qnaComment);
	List<QnaComment> getQnaCommentList();
	void insertQnaComment(QnaComment qnaComment);
	void updateQnaComment(QnaComment qnaComment);
	void deleteQnaComment(QnaComment qnaComment);

}
