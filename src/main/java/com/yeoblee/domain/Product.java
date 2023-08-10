package com.yeoblee.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "product")
@ToString
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pNum;
	
	@Column(nullable = false, length = 100)
	private String pName;
	
	private String pBrand;
	
	private String pType;
	
	@Column(nullable = false)
	private Long pPrice;
	
	private Long pPoint;
	
	@Column(nullable = false)
	private Long pStock;
	
	@Transient
	private MultipartFile pThUploadFile;
	
	private String pThImage;
	
	@Transient
	private MultipartFile[] pDtUploadFile;
	
	@ElementCollection
	@JoinColumn(name = "product_p_num")
	@CollectionTable(name = "product_p_dt_images")
	@Cascade(org.hibernate.annotations.CascadeType.ALL) // 추가된 부분
	private List<String> pDtImages;
	
	@Lob
	private String pDetail;
	
	private String pSize;
	
	private String pOrigin;
	
	@Column(nullable = false)
	private String pDelivery;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date pRegdate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date pUpdate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint default 0")
	private Long pCnt;
	
	@Column(nullable = false)
	private String pStatus;
	

}