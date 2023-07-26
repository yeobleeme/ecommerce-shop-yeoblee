package com.yeoblee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long p_num;
	
	private String p_name;
	
	private Long p_price;
	
	private Long p_discount;
	
	private Long p_stock;
	
	private String p_image1;
	private String p_image2;
	private String p_image3;
	
	private String p_detail;
	private String p_size;
	private String p_origin;
	
	private String p_delivery;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date p_regdate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint default 0")
	private Long p_hit;
	
	private Long p_sell;
		
	@Transient
	private MultipartFile uploadFile;
	
//	private Long p_ref;
//	private Long p_lev;
//	private Long p_seq;

}





