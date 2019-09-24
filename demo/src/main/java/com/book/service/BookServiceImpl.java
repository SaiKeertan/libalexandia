package com.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.bo.BookBo;
import com.book.dao.Booksdao;
import com.book.dto.BooksDTO;
import com.book.model.Book;

@Service
public class BookServiceImpl implements BooksService{
	
	@Autowired
	private Booksdao bookDao;

	@Override
	public Boolean saveBook(BooksDTO book) {
		boolean status=false;
		
		try{
			Book books=new Book(book);
			status=bookDao.save(books);
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean deleteBookById(BookBo book) {
		boolean status=false;
		try{
			status=bookDao.deleteBookById(book);
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean updateById(BookBo books) {
		boolean status=false;
		try{
			status=bookDao.updateBookById(books);
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public BookBo getBookByID(Long bookID) {
		
		BookBo book=bookDao.getBookByID(bookID);
		return book;
	}

}
