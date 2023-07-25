package com.yeoblee.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "boardList")
@Entity
public class Member {

	@Id
	@Column(name = "MEMBER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long m_id;
	
	@Column(unique = true)
	private String m_email;
	
	private String m_name;
	
	private String m_password;
	
	private String m_addr1;
	private String m_addr2;
	private String m_addr3;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	private boolean enabled;

//	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
//	private List<Board> boardList = new ArrayList<>();
}

