package com.yeoblee.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yeoblee.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

//	Page<Member> findByIdContaining(String searchWord, Pageable pageable);
//	Page<Member> findByNameContaining(String searchWord, Pageable pageable);
	
}
