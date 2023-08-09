package com.yeoblee.controller;

import java.io.File;
import java.io.IOException;

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
	
	@PostMapping("/mypage/qna/write")
	public String insertProduct(Product product, @AuthenticationPrincipal SecurityUser pricipal) throws IOException {
		
		// 파일업로드
		MultipartFile pUploadFile = product.getPUploadFile();
		if(!pUploadFile.isEmpty()) {
			String pFileName = pUploadFile.getOriginalFilename();
			pUploadFile.transferTo(new File(pUploadFolder + pFileName));
			product.setPImage1(pFileName);
		}
		
		productService.insertProduct(product);
		
		return "redirect:/admin/product";
	}
	
	

}





