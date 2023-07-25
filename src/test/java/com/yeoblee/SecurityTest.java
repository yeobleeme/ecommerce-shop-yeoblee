package com.yeoblee;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.yeoblee.domain.Member;
import com.yeoblee.domain.Role;
import com.yeoblee.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class SecurityTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void testInsert() {
		Member member = new Member();
		
		member.setM_email("yeoblee@gmail.com");
		member.setM_name("yeoblee");
		member.setM_password(encoder.encode("12345"));
		member.setM_addr1("123-000");
		member.setM_addr2("seoul seocho");
		member.setM_addr3("102-000");
		member.setRole(Role.ROLE_ADMIN);
		
		memberRepository.save(member);
	}
	
}