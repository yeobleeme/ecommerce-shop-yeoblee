package com.yeoblee.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EntityListeners(QnaListeners.class)
@Getter
@Setter
@ToString
@Entity
public class Qna {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qnaNum;
	
	@Column(nullable = false, length = 100)
	private String qnaTitle;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mbrId")
	private Member qnaWriter;
	
	@Lob
	private String qnaContent;
	
	@Transient
	private MultipartFile qnaUploadFile;
	
	private String qnaFileName;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date qnaCrtdate;	
	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint default 0")
	private Long qnaCnt;
	
//	@OneToMany(mappedBy = "qna")
//    private List<QnaComment> qnaComment;
	
}




