package com.yeoblee.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yeoblee.domain.Member;
import com.yeoblee.persistence.MemberRepository;

@Service
public class SecurityUserDetailService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Member> optional = memberRepository.findById(username);
		
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username + " 사용자를 찾지 못했습니다.");
		} else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}

}
