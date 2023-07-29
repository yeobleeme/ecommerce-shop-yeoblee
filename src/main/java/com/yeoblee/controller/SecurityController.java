package com.yeoblee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yeoblee.domain.Member;
import com.yeoblee.service.MemberService;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public void login() {}
	
	@GetMapping("/logout")
	public void logout() {}
	
	@Autowired
	private MemberService memberService;
	
//	@PostMapping("login")
//	public String login(Member member, Model model) {
//		
//		Member findMember = memberService.getMember(member);
//		
//		if(findMember != null && findMember.getMbrPw().equals(member.getMbrPw())) {
//			model.addAttribute("member", findMember);
//			return "info/welcome";
//		} else {
//			return "redirect:login";
//		}
//	}
	
}















