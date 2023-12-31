package com.yeoblee.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String prdUploadFolder;
	
	@Value("${path.download}")
	public String prdDownFolder;
	
	
	@GetMapping("/admin/product/insert")
	public String getInsertProdcut(Product product) {
		
		return "admin/product/insertProduct";
	}
	
	@PostMapping("/admin/product/insert")
	public String insertProduct(Product product, @AuthenticationPrincipal SecurityUser pricipal) throws IOException {
		
		// 대표이미지 (썸네일) 단일파일 업로드
		MultipartFile prdThUploadFile = product.getPrdThUploadFile();
		
		if(!prdThUploadFile.isEmpty()) {
			String prdThFileName = prdThUploadFile.getOriginalFilename();
			prdThUploadFile.transferTo(new File(prdUploadFolder + prdThFileName));
			product.setPrdThImage(prdThFileName);
		}
		
		
		// 상세이미지 다중파일 업로드
		MultipartFile[] prdDtUploadFile = product.getPrdDtUploadFile();
	    
	    List<String> detailImageNames = new ArrayList<>();
	    for (MultipartFile file : prdDtUploadFile) {
	        if (!file.isEmpty()) {
	            String fileName = productService.saveUploadedFile(file);
	            detailImageNames.add(fileName);
	        }
	    }
	    product.setPrdDtImages(detailImageNames);
		
		productService.insertProduct(product);
		
		return "redirect:/admin/product";
	}
	
	
	@GetMapping("/admin/product")
	public String getProductList(Model model,
			@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "10") int rowSizePerPage,
			@RequestParam(defaultValue = "prdName") String searchType,
			@RequestParam(defaultValue = "prdNum") String qnaSeq,
			@RequestParam(defaultValue = "") String searchWord
			) {
		
		Pageable pageable = PageRequest.of(curPage, rowSizePerPage, Sort.by(qnaSeq).descending());
		Page<Product> pagedResult = productService.getProductList(pageable, searchType, searchWord);
		
		int totalRowCount = pagedResult.getNumberOfElements();
		int totalPageCount = pagedResult.getTotalPages();
		int pageSize = pagingInfo.getPageSize();
		int startPage = curPage / pageSize * pageSize + 1;
		int endPage = startPage + pageSize + 1;
		endPage = endPage > totalPageCount ? (totalPageCount > 0 ? totalPageCount : 1) : endPage;
		
		pagingInfo.setCurPage(curPage);
		pagingInfo.setTotalRowCount(totalRowCount);
		pagingInfo.setTotalPageCount(totalPageCount);
		pagingInfo.setStartPage(startPage);
		pagingInfo.setEndPage(endPage);
		pagingInfo.setSearchType(searchType);
		pagingInfo.setSearchWord(searchWord);
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("pagedResult", pagedResult);
		model.addAttribute("pageable", pageable);
		model.addAttribute("cp", curPage);
		model.addAttribute("sp", startPage);
		model.addAttribute("ep", endPage);
		model.addAttribute("ps", pageSize);
		model.addAttribute("rp", rowSizePerPage);
		model.addAttribute("tp", totalPageCount);
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);
		
		return "admin/product/adminProductList";
	}
	
	
	@GetMapping("/admin/product/view")
	public String getProductView(Model model, @RequestParam Long prdNum) {
		
		Product product = new Product();
	    product.setPrdNum(prdNum);
		productService.updatePrdCnt(product);
		model.addAttribute("product", productService.getProduct(product));
		model.addAttribute("prdNum", prdNum);
		
		return "admin/product/adminProductView";
	}
	
	
	@GetMapping("/admin/product/modify")
	public String updateProduct(Model model, Product product) {
		model.addAttribute("product", productService.getProduct(product));
		return "admin/product/updateProduct";
	}
	
	@PostMapping("/admin/product/modify")
	public String updateProduct(Product product, MultipartFile prdThUploadFile, MultipartFile[] prdDtUploadFile, @AuthenticationPrincipal SecurityUser pricipal, Model model) throws IOException {
		
		if(!prdThUploadFile.isEmpty()) {
			String prdThFileName = prdThUploadFile.getOriginalFilename();
			prdThUploadFile.transferTo(new File(prdUploadFolder + prdThFileName));
			product.setPrdThImage(prdThFileName);
		}
		
		List<String> detailImageNames = new ArrayList<>();
	    for (MultipartFile file : prdDtUploadFile) {
	        if (!file.isEmpty()) {
	            String fileName = productService.saveUploadedFile(file);
	            detailImageNames.add(fileName);
	        }
	    }
	    product.setPrdDtImages(detailImageNames);
		
		productService.updateProduct(product);
		
		model.addAttribute("product", productService.getProduct(product));
		
		return "redirect:/admin/product/view?prdNum=" + product.getPrdNum();
	}
	
	
	@GetMapping("/admin/product/delete")
	public String deleteProduct(Product product) {
		
		productService.deleteProduct(product);
		
		return "forward:/admin/product";
	}
	
	
	@GetMapping("/shop")
	public String getShopPage(Model model,
			@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "10") int rowSizePerPage,
			@RequestParam(defaultValue = "prdName") String searchType,
			@RequestParam(defaultValue = "prdNum") String qnaSeq,
			@RequestParam(defaultValue = "") String searchWord
			) {
		
		Pageable pageable = PageRequest.of(curPage, rowSizePerPage, Sort.by(qnaSeq).descending());
		Page<Product> pagedResult = productService.getProductList(pageable, searchType, searchWord);
		
		int totalRowCount = pagedResult.getNumberOfElements();
		int totalPageCount = pagedResult.getTotalPages();
		int pageSize = pagingInfo.getPageSize();
		int startPage = curPage / pageSize * pageSize + 1;
		int endPage = startPage + pageSize + 1;
		endPage = endPage > totalPageCount ? (totalPageCount > 0 ? totalPageCount : 1) : endPage;
		
		pagingInfo.setCurPage(curPage);
		pagingInfo.setTotalRowCount(totalRowCount);
		pagingInfo.setTotalPageCount(totalPageCount);
		pagingInfo.setStartPage(startPage);
		pagingInfo.setEndPage(endPage);
		pagingInfo.setSearchType(searchType);
		pagingInfo.setSearchWord(searchWord);
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("pagedResult", pagedResult);
		model.addAttribute("pageable", pageable);
		model.addAttribute("cp", curPage);
		model.addAttribute("sp", startPage);
		model.addAttribute("ep", endPage);
		model.addAttribute("ps", pageSize);
		model.addAttribute("rp", rowSizePerPage);
		model.addAttribute("tp", totalPageCount);
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);
		
		return "shop/shopMain";
	}
	
	
	@GetMapping("/shop/product")
	public String getProductShop(Model model, @RequestParam Long prdNum) {
		
		Product product = new Product();
	    product.setPrdNum(prdNum); 
		productService.updatePrdCnt(product);
		model.addAttribute("product", productService.getProduct(product));
		model.addAttribute("prdNum", prdNum);
		
		return "shop/productView";
	}
	

}




