package com.yeoblee.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
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

import com.yeoblee.domain.Member;
import com.yeoblee.domain.PagingInfo;
import com.yeoblee.domain.Role;
import com.yeoblee.security.SecurityUser;
import com.yeoblee.service.MemberService;

@Controller
@SessionAttributes("pagingInfo")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public PagingInfo pagingInfo = new PagingInfo();
	
//	@GetMapping("/getMemberList")
//	public String getMemberList(Model model,
//			@RequestParam(defaultValue = "0") int curPage,
//			@RequestParam(defaultValue = "10") int rowSizePerPage,
//			@RequestParam(defaultValue = "name") String searchType,
//			@RequestParam(defaultValue = "") String searchWord
//			) {
//		Pageable pageable = PageRequest.of(curPage, rowSizePerPage, Sort.by(searchType).ascending());
//		Page<Member> pagedResult = memberService.getMemberList(pageable, searchType, searchWord);
//		
//		int totalRowCount = pagedResult.getNumberOfElements();
//		int totalPageCount = pagedResult.getTotalPages();
//		int pageSize = pagingInfo.getPageSize();
//		int startPage = curPage / pageSize * pageSize + 1;
//		int endPage = startPage + pageSize + 1;
//		endPage = endPage > totalPageCount ? (totalPageCount > 0 ? totalPageCount : 1) : endPage;
//		
//		pagingInfo.setCurPage(curPage);
//		pagingInfo.setTotalRowCount(totalRowCount);
//		pagingInfo.setTotalPageCount(totalPageCount);
//		pagingInfo.setStartPage(startPage);
//		pagingInfo.setEndPage(endPage);
//		pagingInfo.setSearchType(searchType);
//		pagingInfo.setSearchWord(searchWord);
//		
//		model.addAttribute("pagingInfo", pagingInfo);
//		model.addAttribute("pagedResult", pagedResult);
//		model.addAttribute("pageable", pageable);
//		model.addAttribute("cp", curPage);
//		model.addAttribute("sp", startPage);
//		model.addAttribute("ep", endPage);
//		model.addAttribute("ps", pageSize);
//		model.addAttribute("rp", rowSizePerPage);
//		model.addAttribute("tp", totalPageCount);
//		model.addAttribute("st", searchType);
//		model.addAttribute("sw", searchWord);
//		
//		return "member/getMemberList";
//	}
	

	@GetMapping("/addMember")
	public String getaddMemberPage() {
		return "member/addMember";
	}
	
	@PostMapping("/addMember")
	public String addMember(Member member) {
//		if(member.getId() == null) {
//			return "redirect:login";
//		}
		if (member.getRole() == null) {
            member.setRole(Role.ROLE_USER);
        }
		memberService.addMember(member);
		return "redirect:login";
	}
	
	
	@GetMapping("/mypage/member")
	public String infoMember(@AuthenticationPrincipal SecurityUser pricipal) {
		return "member/infoMember";
	}
	
	@GetMapping("/mypage/member/modify")
	public String updateMember(@AuthenticationPrincipal SecurityUser pricipal) {
		return "member/updateMember";
	}
	
	@PostMapping("/mypage/member/modify")
	public String updateMember(Member member, @AuthenticationPrincipal SecurityUser pricipal) {
		memberService.updateMember(member);
		return "redirect:/mypage/member";
	}
	
	
//	@GetMapping("/deleteMember")
//	public String deleteMember(Member member) {
//		if(member.getId() == null) {
//			return "redirect:login";
//		}
//		
//		memberService.deleteMember(member);
//		
//		return "redirect:getMemberList";
//	}
	

}














