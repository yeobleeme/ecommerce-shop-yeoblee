package com.yeoblee.service;

import com.yeoblee.domain.Member;

public interface MemberService {

	long getTotalRowCount(Member member);
	Member getMember(Member member);
//	Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord);
	void addMember(Member member);
	void updateMemberPw(Member member);
	void deleteMember(Member member);

}