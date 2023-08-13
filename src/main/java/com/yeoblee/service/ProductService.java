package com.yeoblee.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.yeoblee.domain.Product;

public interface ProductService {
	
	long getTotalRowCount(Product product);
	Product getProduct(Product product);
	Page<Product> getProductList(Pageable pageable, String searchType, String searchWord);
	void insertProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Product product);
	int updatePrdCnt(Product product);
	
	String saveUploadedFile(MultipartFile file) throws IOException;

}