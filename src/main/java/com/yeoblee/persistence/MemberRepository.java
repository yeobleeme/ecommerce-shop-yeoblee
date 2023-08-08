package com.yeoblee.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yeoblee.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findByMbrId(String mbrId);
	
//    Page<Member> findByIdContaining(String searchWord, Pageable pageable);
//    Page<Member> findByNameContaining(String searchWord, Pageable pageable);
    
}

