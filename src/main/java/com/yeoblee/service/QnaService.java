package com.yeoblee.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yeoblee.domain.Qna;

public interface QnaService {
	
	long getTotalRowCount(Qna qna);
	Qna getQna(Qna qna);
	Page<Qna> getQnaList(Pageable pageable, String searchType, String searchWord);
	void insertQna(Qna qna);
	void updateQna(Qna qna);
	void deleteQna(Qna qna);
	int updateQnaCnt(Qna qna);

}