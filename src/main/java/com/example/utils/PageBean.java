package com.example.utils;

public class PageBean {
	private Integer pageSize;
	private Integer totalPage;
	private Integer currentPage;
	private Integer totalRecord;

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getTotalPage() {
		return (this.totalPage - 1) / this.pageSize + 1;
	}
}
