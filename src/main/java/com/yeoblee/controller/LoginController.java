package com.yeoblee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yeoblee.domain.Member;
import com.yeoblee.service.MemberService;

@Controller
@SessionAttributes("member")
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		
		Member findMember = memberService.getMember(member);
		
		if(findMember != null && findMember.getMbrPw().equals(member.getMbrPw())) {
			model.addAttribute("member", findMember);
			return "/";
		} else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "/";
	}
	
//	@GetMapping("/join")
//	public String getJoinPage() {
//		return "join";
//	}
	

}










