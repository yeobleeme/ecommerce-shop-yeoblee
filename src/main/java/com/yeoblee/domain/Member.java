package com.yeoblee.domain;

import java.util.ArrayList;
import java.util.Date;
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
@ToString
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long m_num;
	
	@Column(name = "M_ID", unique = true)
	private String m_id;
	
	private String m_name;
	
	@Column(unique = true)
	private String m_email;
	
	private boolean m_email_ok;
	
	private String m_password;
	
	private String m_phone;
	
	private boolean m_sms_ok;
	
	private String m_addr1;
	private String m_addr2;
	private String m_addr3;
	
	private Date m_birth;
	
	private Long m_point;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date m_regdate;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	private boolean enabled;

//	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
//	private List<Board> boardList = new ArrayList<>();
	
}

