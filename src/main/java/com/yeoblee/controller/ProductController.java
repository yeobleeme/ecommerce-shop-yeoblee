package com.yeoblee.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.yeoblee.domain.PagingInfo;
import com.yeoblee.domain.Product;
import com.yeoblee.security.SecurityUser;
import com.yeoblee.service.ProductService;

@Controller
@SessionAttributes({"pagingInfo"})
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	Environment environment;
	
	public PagingInfo pagingInfo = new PagingInfo();
	
	@Value("${path.upload}")
	public String pUploadFolder;
	
	@Value("${path.download}")
	public String pDownFolder;
	
	@GetMapping("/admin/product/insert")
	public String getInsertProdcut(Product product) {
		
		return "admin/product/insertProduct";
	}
	
	@PostMapping("/admin/product/insert")
	public String insertProduct(Product product, @AuthenticationPrincipal SecurityUser pricipal) throws IOException {
		
		// 대표이미지 (썸네일) 파일 업로드
		MultipartFile pUploadFile = product.getPUploadFile();
		
		if(!pUploadFile.isEmpty()) {
			String pFileName = pUploadFile.getOriginalFilename();
			pUploadFile.transferTo(new File(pUploadFolder + pFileName));
			product.setPImageTh(pFileName);
		}
		
		
		// 상세이미지 다중파일 업로드
		MultipartFile[] pUploadFiles = product.getPUploadFiles();
	    
	    List<String> detailImageNames = new ArrayList<>(); // 상세이미지 파일명 리스트
	    for (MultipartFile file : pUploadFiles) {
	        if (!file.isEmpty()) {
	            String fileName = productService.saveUploadedFile(file); // ProductService의 saveUploadedFile 메서드 호출
	            detailImageNames.add(fileName);
	        }
	    }
	    product.setPImagesDt(detailImageNames); // 엔티티 필드에 파일명 리스트 저장
		
		productService.insertProduct(product);
		
		return "redirect:/admin/product";
	}
	
	

}





