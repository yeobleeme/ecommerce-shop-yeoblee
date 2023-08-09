package com.yeoblee.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "product_images_dt")
@ToString
public class ProductImagesDt {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long pImgsNum;
//	
//	 @ManyToOne
//	 @JoinColumn(name = "pNum")
//	 private Product product;
//	 
//	 private String imageName;

}
