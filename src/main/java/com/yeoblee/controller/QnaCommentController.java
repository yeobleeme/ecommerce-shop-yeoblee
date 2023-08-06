package com.yeoblee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeoblee.domain.Qna;
import com.yeoblee.domain.QnaComment;
import com.yeoblee.security.SecurityUser;
import com.yeoblee.service.QnaCommentService;

@Controller
public class QnaCommentController {
	
	@Autowired
	private QnaCommentService qnaCommentService;
	
	@PostMapping("/mypage/qna/view/comment")
	public String insertQnaComment(@RequestParam Long qnaNum, QnaComment qnaComment, @AuthenticationPrincipal SecurityUser principal) {
				
		Qna qna = new Qna();
	    qna.setQnaNum(qnaNum);
	    qnaComment.setQna(qna);
	    
	    qnaComment.setQnaCommentWriter(principal.getMember());
	    
		qnaCommentService.insertQnaComment(qnaComment);
		
		return "redirect:/mypage/qna/view?qnaNum=" + qnaNum;
	}

}
