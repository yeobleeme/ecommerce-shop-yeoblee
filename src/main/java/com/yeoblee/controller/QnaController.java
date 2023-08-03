package com.yeoblee.controller;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.yeoblee.domain.Member;
import com.yeoblee.domain.PagingInfo;
import com.yeoblee.domain.Qna;
import com.yeoblee.security.SecurityUser;
import com.yeoblee.service.QnaService;

@Controller
@SessionAttributes({"pagingInfo"})
//@RequestMapping("/qna/")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	Environment environment;
	
	public PagingInfo pagingInfo = new PagingInfo();
	
	@Value("${path.upload}")
	public String qnaUploadFolder;
	
	@Value("${path.download}")
	public String qnaDownFolder;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/mypage/qna/write")
	public String getQnaWrite(Model model, Member member) {
		return "qna/qnaWrite";
	}
	
//	@PostMapping("/mypage/qna/write")
//	public String insertQna(@ModelAttribute("member") Member member, Qna qna) throws IOException {
//		
//		// 파일업로드
//		MultipartFile qnaUploadFile = qna.getQnaUploadFile();
//		if(!qnaUploadFile.isEmpty()) {
//			String qnaFileName = qnaUploadFile.getOriginalFilename();
//			qnaUploadFile.transferTo(new File(uploadFolder + qnaFileName));
//			qna.setQnaFileName(qnaFileName);
//		}
//		
////		 qna.setQnaMobile(member.getMbrMobile());
////		 qna.setQnaEmail(member.getMbrEmail());
//		
//		qnaService.insertQna(qna);
//		return "redirect:/mypage/qna";
//	}
	
	
	@PostMapping("/mypage/qna/write")
	public String insertQna(Qna qna, @AuthenticationPrincipal SecurityUser pricipal) throws IOException {
		
		// 파일업로드
		MultipartFile qnaUploadFile = qna.getQnaUploadFile();
		if(!qnaUploadFile.isEmpty()) {
			String qnaFileName = qnaUploadFile.getOriginalFilename();
			qnaUploadFile.transferTo(new File(qnaUploadFolder + qnaFileName));
			qna.setQnaFileName(qnaFileName);
		}
		
		qna.setQnaWriter(pricipal.getMember());
		
		qnaService.insertQna(qna);
		
		return "redirect:/mypage/qna";
	}
	
	
	@GetMapping("/mypage/qna")
	public String getQnaList(Model model,
			@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "10") int rowSizePerPage,
			@RequestParam(defaultValue = "qnaTitle") String searchType,
			@RequestParam(defaultValue = "qnaNum") String qnaSeq,
			@RequestParam(defaultValue = "") String searchWord
			) {
		Pageable pageable = PageRequest.of(curPage, rowSizePerPage, Sort.by(qnaSeq).descending());
		Page<Qna> pagedResult = qnaService.getQnaList(pageable, searchType, searchWord);
		
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
		
		return "qna/qna";
	}

	
	@GetMapping("/mypage/qna/view")
	public String getQnaView(Model model, @RequestParam Long qnaNum) {
		Qna qna = new Qna();
	    qna.setQnaNum(qnaNum);
		qnaService.updateQnaCnt(qna);
		model.addAttribute("qna", qnaService.getQna(qna));
		return "qna/qnaView";
	}
	
	
	@GetMapping("/mypage/qna/modify")
	public String updateQna(Model model, Qna qna) {
		model.addAttribute("qna", qnaService.getQna(qna));
		return "qna/qnaModify";
	}
	
	
	@PostMapping("/mypage/qna/modify")
	public String updateQna(Qna qna, MultipartFile qnaUploadFile, @AuthenticationPrincipal SecurityUser pricipal, Model model) throws IOException {
		
		if (!qnaUploadFile.isEmpty()) {
	        String qnaFileName = qnaUploadFile.getOriginalFilename();
	        qnaUploadFile.transferTo(new File(qnaUploadFolder + qnaFileName));
	        qna.setQnaFileName(qnaFileName);
	    }
		
		qnaService.updateQna(qna);
		
		model.addAttribute("qna", qnaService.getQna(qna));
		
		return "redirect:/mypage/qna/view?qnaNum=" + qna.getQnaNum();
	}
	

	

}
