package model;

import java.util.List;

public class ResultSQLProducts {
	private List<Product> products;
	private int maxPage;
	private int pageNumber;
	
	public ResultSQLProducts(List<Product> products, int maxPage, int pageNumber) {
		super();
		this.products = products;
		this.maxPage = maxPage;
		this.pageNumber = pageNumber;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}	
}
