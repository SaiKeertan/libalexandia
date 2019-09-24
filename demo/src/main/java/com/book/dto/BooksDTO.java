package com.book.dto;

import java.io.Serializable;

public class BooksDTO implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
		
	 private String author;
	
	 private Double price;
	
	 private String link;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
	 

}
