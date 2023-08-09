package com.yeoblee.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		
		if(findProduct.isPresent())
			return findProduct.get();
		
		else return null;
	}

	@Override
	public Page<Product> getProductList(Pageable pageable, String searchType, String searchWord) {
		
		if(searchType.equalsIgnoreCase("pName")) {
			return productRepository.findByPNameContaining(searchWord, pageable);
			
		} else if(searchType.equalsIgnoreCase("pBrand")) {
			return productRepository.findByPBrandContaining(searchWord, pageable);
			
		} else {
			return productRepository.findByPTypeContaining(searchWord, pageable);
		}
	}

	@Override
	public void insertProduct(Product product) {
		
		productRepository.save(product);
	}

//	@Override
//	public void updateProduct(Product product) {
//		
//		Product findProduct = productRepository.findById(product.getPNum()).get();
//		
//		findProduct.setPName(product.getPName());
//		findProduct.setPBrand(product.getPBrand());
//		findProduct.setPType(product.getPType());
//		findProduct.setPPrice(product.getPPrice());
//		findProduct.setPPoint(product.getPPoint());
//		findProduct.setPStock(product.getPStock());
//		findProduct.setPDetail(product.getPDetail());
//		findProduct.setPSize(product.getPSize());
//		findProduct.setPOrigin(product.getPOrigin());
//		findProduct.setPDelivery(product.getPDelivery());
//		findProduct.setPStatus(product.getPStatus());
//		
//		productRepository.save(findProduct);
//	}
	
	@Override
	public void updateProduct(Product product) {
		
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Product product) {

		productRepository.deleteById(product.getPNum());
	}

	@Override
	public int updatePCnt(Product product) {
		
		return productRepository.updatePCnt(product.getPNum());
	}
	
	

}