package com.yeoblee.service;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.yeoblee.domain.Member;
import com.yeoblee.security.SecurityUser;

public interface MemberService {

	long getTotalRowCount(Member member);
	Member getMember(Member member);
//	Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord);
	void addMember(Member member);
	void updateMemberPw(Member member);
	void deleteMember(Member member);

}