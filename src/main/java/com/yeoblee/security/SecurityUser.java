package com.yeoblee.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.yeoblee.domain.Member;

public class SecurityUser extends User {
	
	private Member member;

	public SecurityUser(Member member) {
		super(member.getMbrId(), member.getMbrPw(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	public Member getMember() {
		return this.member;
	}
	
	public void updateMember(Member member) {
		
		this.member.setMbrId(member.getMbrId());
		
//		if (!member.getMbrPw().isEmpty()) {
//	        String encodedPassword = passwordEncoder.encode(member.getMbrPw());
//	        this.member.setMbrPw(encodedPassword);
//	    }
				
		this.member.setMbrName(member.getMbrName());
		this.member.setMbrMobile(member.getMbrMobile());
		this.member.setMbrEmail(member.getMbrEmail());
		this.member.setMbrAddr1(member.getMbrAddr1());
		this.member.setMbrAddr2(member.getMbrAddr2());
		
	}
	
}
