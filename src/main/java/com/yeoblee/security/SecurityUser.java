package com.yeoblee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yeoblee.domain.Member;
import com.yeoblee.persistence.MemberRepository;

public class SecurityUser extends User {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		
		String encodedPassword = passwordEncoder.encode(member.getMbrPw());
		this.member.setMbrPw(encodedPassword);
		
//		this.member.setMbrPw(member.getMbrPw());
		
		this.member.setMbrName(member.getMbrName());
		this.member.setMbrMobile(member.getMbrName());
		this.member.setMbrEmail(member.getMbrEmail());
		this.member.setMbrAddr1(member.getMbrAddr1());
		this.member.setMbrAddr2(member.getMbrAddr2());
		
		memberRepository.save(member);
	}
	
}
