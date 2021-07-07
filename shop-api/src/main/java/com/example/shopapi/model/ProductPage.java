package com.example.shopapi.model;

import org.springframework.data.domain.Sort;

public class ProductPage {
	private int pageNumber = 0;
	private int PageSize = 10;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy = "price";

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public Sort.Direction getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
}
