package com.book.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.book.bo.BookBo;
import com.book.dto.BooksDTO;
import com.book.model.Book;

@Repository
public class BooksDaoImpl implements Booksdao{

	
	EntityManager entitymanager;
	
	public EntityManager getEntitymanager() {
		return entitymanager;
	}
	
	@PersistenceContext
	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}
	
	@Override
	public BookBo getBookByID(Long bookID)
	{
		BookBo bookbo=new BookBo();
			StringBuilder builder = new StringBuilder();
			Query query = null;
			builder.append("select id,title,author,link,price from BOOK where id='" + bookID + "'");
			query=getEntitymanager().createNativeQuery(builder.toString(),BookBo.class);
			bookbo=  (BookBo) query.getSingleResult();
		return bookbo;
	}
	@Transactional
	public boolean save(Book book) {
		 boolean status=true;
		try {
		getEntitymanager().merge(book);
		}catch(Exception e){
			e.printStackTrace();
			status=false;
		}
		return status;
	}
	@Transactional
	public boolean deleteBookById(BookBo book) {

		 boolean status=true;
			try {
				StringBuilder builder = new StringBuilder();
				Query query = null;
				builder.append("DELETE  FROM BOOK where id='" + book.getId() + "'");
				query=getEntitymanager().createNativeQuery(builder.toString());
				query.executeUpdate();
			}catch(Exception e)
			{
				status=false;
			}
			return status;
	
	}
	@Transactional
	public boolean updateBookById(BookBo books) {
		 boolean status=true;
			try {
				StringBuilder builder = new StringBuilder();
				Query query = null;
				builder.append("update  BOOK set title = '" + books.getBookTitle()+ "',author = '" + books.getAuthor()+ "',link = '" + books.getLink()+ "',price = '" + books.getPrice()+ "' where id='" + books.getId() +"'");
				query=getEntitymanager().createNativeQuery(builder.toString());
				query.executeUpdate();
			}catch(Exception e)
			{
				status=false;
			}
			return status;
	}

	
	
}
