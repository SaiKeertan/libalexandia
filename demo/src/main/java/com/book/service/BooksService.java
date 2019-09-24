package com.book.service;

import com.book.bo.BookBo;
import com.book.dto.BooksDTO;


public interface BooksService {
	public BookBo getBookByID(Long bookID);
	public Boolean saveBook(BooksDTO book);
	public boolean deleteBookById(BookBo book);
	public boolean updateById(BookBo books);
		

}
