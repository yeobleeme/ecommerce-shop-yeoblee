package com.yeoblee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "qna")
@Table(name = "qna_comment")
@Entity
public class QnaComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qcNum;
	
	private String qcWriterId;
	
	@Lob
	@Column(columnDefinition = "TEXT", nullable = false)
	private String qcContent;
	 
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date qcRegdate;	
	 
	@ManyToOne
	@JoinColumn(name = "qnaNum", referencedColumnName = "qnaNum")
	private Qna qna;
	 
	@ManyToOne
	@JoinColumn(name = "mbrNum")
	private Member qnaCommentWriter;

	
}