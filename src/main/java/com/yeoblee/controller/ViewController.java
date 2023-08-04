package com.yeoblee.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeoblee.domain.Qna;
import com.yeoblee.security.SecurityUser;

@Controller
public class ViewController {
	
	@GetMapping("/mypage/myPage")
	public String getMypage(@AuthenticationPrincipal SecurityUser pricipal) {
		return "mypage/myPage";
	}
	

}
