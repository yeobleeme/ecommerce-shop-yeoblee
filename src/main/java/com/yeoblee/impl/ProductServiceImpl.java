package com.yeoblee.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeoblee.domain.Product;
import com.yeoblee.persistence.ProductRepository;
import com.yeoblee.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public long getTotalRowCount(Product product) {
		return productRepository.count();
	}

	@Override
	public Product getProduct(Product product) {
		
		Optional<Product> findProduct = productRepository.findById(product.getPNum());
		if(findProduct.isPresent()) return findProduct.get();
		else return null; 
	}

//	@Override
//	public Page<Product> getProductList(Pageable pageable, String searchType, String searchWord) {
//		
//		if(searchType.equalsIgnoreCase("p_name")) {
//			return productRepository.findByTitleContaining(searchWord, pageable);
//		} else if(searchType.equalsIgnoreCase("writer")) {
//			return productRepository.findByWriterContaining(searchWord, pageable);
//		} else {
//			return productRepository.findByContentContaining(searchWord, pageable);
//		}
//	}

	@Override
	public void insertProduct(Product product) {
		productRepository.save(product);
//		productRepository.updateLastSeq(0L, 0L, product.getP_num());
	}

	@Override
	public void updateProduct(Product product) {
		
//		Product findProduct = productRepository.findById(product.getPNum()).get();
//		findProduct.setP_name(product.getP_name());
//		findProduct.setP_detail(product.getP_detail());
//		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
//		productRepository.deleteById(product.getP_num());
	}

//	@Override
//	public int updateReadCount(Product product) {
//		return productRepository.updateReadCount(product.getP_num());
//	}

//	@Override
//	public List<Product> getProductList() {
//		return (List<Product>) productRepository.findAll();
//	}

}