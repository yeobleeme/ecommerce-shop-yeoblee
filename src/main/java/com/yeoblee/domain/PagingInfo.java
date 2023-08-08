package com.yeoblee.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingInfo {
	
	public int curPage = 1;
	private int rowSizePerPage = 10;
	private int pageSize = 10;
	private int totalRowCount; 
	private int firstRow;
	private int lastRow;
	private int totalPageCount;
	private int startPage;
	private int endPage;
	private String searchType;
	private String searchWord;
	
	
	public void pageSetting() {
		
		totalPageCount = (totalRowCount - 1) / rowSizePerPage + 1;
		firstRow = (curPage - 1) * rowSizePerPage;
		lastRow = firstRow + rowSizePerPage;
		if(lastRow > totalRowCount) lastRow = totalRowCount;
		startPage = (curPage - 1) / pageSize + pageSize + 1;
		endPage = startPage + pageSize - 1;
		if(endPage > totalPageCount) endPage = totalPageCount;
		
	}

}