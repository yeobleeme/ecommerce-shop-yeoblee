package com.yeoblee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends	WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private SecurityUserDetailService userDetailService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
//		security.userDetailsService(userDetailService);
		
		security.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/mypage/**").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN");

		security.csrf().disable();
		security.formLogin().loginPage("/login").defaultSuccessUrl("/", true); 
		security.exceptionHandling().accessDeniedPage("/system/accessDenied"); 
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	

}
