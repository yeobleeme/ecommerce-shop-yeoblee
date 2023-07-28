package com.yeoblee.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yeoblee.domain.Member;
import com.yeoblee.persistence.MemberRepository;
import com.yeoblee.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepository.findById(member.getMbrId());
		System.out.println(findMember);
		if(findMember.isPresent())
			return findMember.get();
		else return null;
	}
	
//	@Override
//	public Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord) {
//		if(searchType.equalsIgnoreCase("m_id")) {
//			return memberRepository.findByIdContaining(searchWord, pageable);
//		} else {
//			return memberRepository.findByNameContaining(searchWord, pageable);
//		}
//	}
	
	@Override
	public long getTotalRowCount(Member member) {
		return memberRepository.count();
	}

	@Override
	public void addMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void updateMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void deleteMember(Member member) {
		memberRepository.deleteById(member.getMbrId());
	}

}






