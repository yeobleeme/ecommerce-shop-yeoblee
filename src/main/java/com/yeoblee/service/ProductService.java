package com.yeoblee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yeoblee.domain.Product;

public interface ProductService {
	
	long getTotalRowCount(Product product);
	Product getProduct(Product product);
//	Page<Product> getProductList(Pageable pageable, String searchType, String searchWord);
//	List<Product> getProductList();
	void insertProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Product product);
//	int updateReadCount(Product product);

}

