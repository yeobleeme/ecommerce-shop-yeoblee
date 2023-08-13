package com.yeoblee.domain;

import java.time.LocalDateTime;
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
	@Column(name = "prd_num")
	private Long prdNum;
	
	@Column(nullable = false, length = 100)
	private String prdName;
	
	private String prdBrand;
	
	private String prdType;
	
	@Column(nullable = false)
	private Long prdPrice;
	
	private Long prdPoint;
	
	@Column(nullable = false)
	private Long prdStock;
	
	@Transient
	private MultipartFile prdThUploadFile;
	
	private String prdThImage;
	
	@Transient
	private MultipartFile[] prdDtUploadFile;

	@ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "prd_num"))
    @Column(name = "prd_dt_images")
    private List<String> prdDtImages;
	
	@Lob
	private String prdDetail;
	
	private String prdSize;
	
	private String prdOrigin;
	
	@Column(nullable = false)
	private String prdDelivery;
	
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime prdRegdate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime prdUpdate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint default 0")
	private Long prdCnt;
	
	@Column(nullable = false)
	private String prdStatus;
	

}