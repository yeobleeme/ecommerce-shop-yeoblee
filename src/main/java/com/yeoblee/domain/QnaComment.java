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
@ToString
@Table(name = "qnacomment")
@Entity
public class QnaComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qnaCommentId;
	
	private String qnaCommentWriterId;
	
	@Lob
	@Column(columnDefinition = "TEXT", nullable = false)
	private String qnaComment;
	 
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date qnaCreateDate;	
	 
	@ManyToOne
	@JoinColumn(name = "qnaNum")
	private Qna qnaNum;
	 
	@ManyToOne
	@JoinColumn(name = "mbrNum")
	private Member qnaCommentWriter;

	
}
