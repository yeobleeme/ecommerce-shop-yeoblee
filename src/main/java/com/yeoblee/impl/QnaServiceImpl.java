package com.yeoblee.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yeoblee.domain.Qna;
import com.yeoblee.persistence.QnaRepository;
import com.yeoblee.service.QnaService;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaRepository qnaRepository;

	@Override
	public long getTotalRowCount(Qna qna) {
		return qnaRepository.count();
	}

	@Override
	public Qna getQna(Qna qna) {
		Optional<Qna> findQna = qnaRepository.findById(qna.getQnaNum());
		if(findQna.isPresent())
			return findQna.get();
		else return null;
	}

	@Override
	public Page<Qna> getQnaList(Pageable pageable, String searchType, String searchWord) {
		if(searchType.equalsIgnoreCase("qnaTitle")) {
			return qnaRepository.findByQnaTitleContaining(searchWord, pageable);
		} else if(searchType.equalsIgnoreCase("qnaWriter")) {
			return qnaRepository.findByQnaWriterContaining(searchWord, pageable);
		} else {
			return qnaRepository.findByQnaContentContaining(searchWord, pageable);
		}
	}

	@Override
	public void insertQna(Qna qna) {
		qnaRepository.save(qna);
//		qnaRepository.updateQnaLastSeq(0L, 0L, qna.getQnaNum());
	}

	@Override
	public void updateQna(Qna qna) {
		Qna findQna = qnaRepository.findById(qna.getQnaNum()).get();
		findQna.setQnaTitle(qna.getQnaTitle());
		findQna.setQnaContent(qna.getQnaContent());
		findQna.setQnaMobile(qna.getQnaMobile());
		findQna.setQnaEmail(qna.getQnaEmail());
		qnaRepository.save(findQna);
	}
	
	@Override
	public void deleteQna(Qna qna) {
		qnaRepository.deleteById(qna.getQnaNum());
	}

	@Override
	public int updateQnaCnt(Qna qna) {
		return qnaRepository.updateQnaCnt(qna.getQnaNum());
	}

}





