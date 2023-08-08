package com.yeoblee.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@PostMapping("/mypage/qna/comment/modify")
	public String updateQna(@RequestParam Long qnaNum, QnaComment qnaComment, @AuthenticationPrincipal SecurityUser principal) throws IOException {
		
		Qna qna = new Qna();
	    qna.setQnaNum(qnaNum);
	    qnaComment.setQna(qna);
	    
	    qnaComment.setQnaCommentWriter(principal.getMember());
		
		qnaCommentService.updateQnaComment(qnaComment);
		
		return "redirect:/mypage/qna/view?qnaNum=" + qna.getQnaNum();
	}
	
	
	@GetMapping("/mypage/qna/comment/delete")
	public String deleteQnaComment(@RequestParam Long qnaNum, @RequestParam Long qcNum) {
		
		QnaComment qnaComment = new QnaComment();
	    qnaComment.setQcNum(qcNum);

	    Qna qna = new Qna();
	    qna.setQnaNum(qnaNum);

	    qnaCommentService.deleteQnaComment(qnaComment);
	    
	    return "redirect:/mypage/qna/view?qnaNum=" + qnaNum;
	}







}
