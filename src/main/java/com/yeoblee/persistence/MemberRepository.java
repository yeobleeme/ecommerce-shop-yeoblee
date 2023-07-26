package com.yeoblee.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yeoblee.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	
}
