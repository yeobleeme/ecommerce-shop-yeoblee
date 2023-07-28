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

	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long mbrNum;
	
	@Id
	@Column(unique = true)
	private String mbrId;
	
	private String mbrName;
	
	@Column(unique = true)
	private String mbrEmail;
	
//	private boolean m_email_ok;
	
	private String mbrPw;
	
	@Column(unique = true)
	private String mbrMobile;
	
//	private boolean m_sms_ok;
	
	private String mbrAddr1;
	private String mbrAddr2;
	private String mbrAddr3;
	
	private String mbrBirth;
	
//	@Column(insertable = false, columnDefinition = "bigint default 0")
//	private Long m_point;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date mbrRegDate;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	private boolean enabled;

//	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
//	private List<Board> boardList = new ArrayList<>();
	
}

