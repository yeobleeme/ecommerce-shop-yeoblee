package com.yeoblee.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		
		Optional<Product> findProduct = productRepository.findById(product.getPrdNum());
		
		if(findProduct.isPresent())
			return findProduct.get();
		
		else return null;
	}

	@Override
	public Page<Product> getProductList(Pageable pageable, String searchType, String searchWord) {
		
		if(searchType.equalsIgnoreCase("pName")) {
			return productRepository.findBypNameContaining(searchWord, pageable);
			
		} else if(searchType.equalsIgnoreCase("pBrand")) {
			return productRepository.findBypBrandContaining(searchWord, pageable);
			
		} else {
			return productRepository.findBypTypeContaining(searchWord, pageable);
		}
	}

	@Override
	public void insertProduct(Product product) {
		
		productRepository.save(product);
	}
	
	@Value("${path.upload}")
    private String pUploadFolder;
	
	@Override
    public String saveUploadedFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = pUploadFolder + fileName;
        file.transferTo(new File(filePath));
        return fileName;
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
		
		product.setPUpdate(LocalDateTime.now());
		
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Product product) {

		productRepository.deleteById(product.getPrdNum());
	}

	@Override
	public int updatePCnt(Product product) {
		
		return productRepository.updatePCnt(product.getPrdNum());
	}
	
	

}