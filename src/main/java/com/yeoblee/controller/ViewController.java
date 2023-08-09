package com.yeoblee.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yeoblee.security.SecurityUser;

@Controller
public class ViewController {
	
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