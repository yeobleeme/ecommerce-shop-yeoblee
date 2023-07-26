package com.yeoblee.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.yeoblee.domain.Member;

public class SecurityUser extends User {
	
	private Member member;

	public SecurityUser(Member member) {
		super(member.getM_id(), member.getM_password(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	public Member getMember() {
		return this.member;
	}
}
