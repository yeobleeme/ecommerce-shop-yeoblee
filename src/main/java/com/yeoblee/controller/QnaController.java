package com.yeoblee.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.yeoblee.domain.Member;
import com.yeoblee.domain.PagingInfo;
import com.yeoblee.domain.Qna;
import com.yeoblee.service.QnaService;

@Controller
@SessionAttributes({"pagingInfo"})
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	Environment environment;
	
	public PagingInfo pagingInfo = new PagingInfo();
	
	@Value("${path.upload}")
	public String uploadFolder;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/mypage/qna/write")
	public String getQnaWrite() {
		return "mypage/qnaWrite";
	}
	
	@PostMapping("/mypage/qna/write")
	public String insertQna(@ModelAttribute("member") Member member, Qna qna) throws IOException {
		if (member.getMbrId() == null) {
			return "redirect:login";
		}	
		// 파일업로드
		MultipartFile qnaUploadFile = qna.getQnaUploadFile();
		if(!qnaUploadFile.isEmpty()) {
			String qnaFileName = qnaUploadFile.getOriginalFilename();
			qnaUploadFile.transferTo(new File(uploadFolder + qnaFileName));
			qna.setQnaFileName(qnaFileName);
		}	
		
		qnaService.insertQna(qna);
		return "redirect:/mypage/qna";
	}
	
	

}
