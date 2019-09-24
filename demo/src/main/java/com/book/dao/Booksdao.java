package com.book.dao;

import com.book.bo.BookBo;
import com.book.dto.BooksDTO;
import com.book.model.Book;



public interface Booksdao {

	public boolean updateBookById(BookBo books);
	public BookBo getBookByID(Long bookID);
	public boolean save(Book book) ;
	public boolean deleteBookById(BookBo book);

}
