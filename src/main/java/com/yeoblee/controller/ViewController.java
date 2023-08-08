package com.yeoblee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeoblee.domain.Member;
import com.yeoblee.domain.Qna;
import com.yeoblee.security.SecurityUser;
import com.yeoblee.service.MemberService;

@Controller
public class ViewController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/mypage/myPage")
	public String getMypage(@AuthenticationPrincipal SecurityUser pricipal) {
		
		return "mypage/myPage";
	}
	
	@GetMapping("/admin")
	public String getAdminPage(@AuthenticationPrincipal SecurityUser pricipal) {
		
		return "admin/admin";
	}
	
	
//	@GetMapping("/mypage/myPage")
//	public String getMypage(@AuthenticationPrincipal SecurityUser pricipal, Model model, @RequestParam Long mbrNum) {
//		Member member = new Member();
//	    member.setMbrNum(mbrNum);
//		model.addAttribute("member", memberService.getMember(member));
//		return "mypage/myPage";
//	}
	

}