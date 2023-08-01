package com.yeoblee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/mypage/myPage")
	public String getMypage() {
		return "mypage/myPage";
	}
	
	@GetMapping("/mypage/qna")
	public String getQnaPage() {
		return "mypage/qna";
	}
	

}
