package com.book.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.bo.BookBo;
import com.book.dao.Booksdao;
import com.book.dto.BookNotFoundException;
import com.book.dto.BooksDTO;
import com.book.dto.ResponseDTO;
import com.book.service.BooksService;

@RestController
public class BookController {
	
	@Autowired
	BooksService bookservice;
	

// Create a new Book
    @PostMapping("/books")
    public ResponseDTO  createNote(@RequestBody BooksDTO book) {
    	boolean status = true;
		ResponseDTO responceDto = new ResponseDTO();
    	try{
    		bookservice.saveBook(book);
    		if (status) {
    			responceDto.setStatusCode("200");
    			responceDto.setStatusMessage("New Book Created Successfully.");
    		} else {
    			responceDto.setStatusCode("500");
    			responceDto.setStatusMessage("Book Creation Failed.");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return responceDto;
    }


// Update a Book
    @PutMapping("/updateBook/{id}")
    public ResponseDTO updateBook(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody BooksDTO bookDetails) throws BookNotFoundException {

    	ResponseDTO responceDto = new ResponseDTO();
        
BookBo book = bookservice.getBookByID(bookId);

        book. setAuthor(bookDetails.getAuthor());
        book.setBookTitle(bookDetails.getTitle());
        book.setLink(bookDetails.getLink());
        book.setPrice(bookDetails.getPrice());

boolean updatedBook = bookservice.updateById(book);
if (updatedBook) {
	responceDto.setStatusCode("200");
	responceDto.setStatusMessage("Book updated Successfully.");
} else {
	responceDto.setStatusCode("500");
	responceDto.setStatusMessage("Book not exists to update for the ID."+bookId);
}
return responceDto;
    }

// Delete a Book
    @DeleteMapping("/deleteBook/{id}")
    public ResponseDTO deleteBook(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
    	BookBo book = bookservice.getBookByID(bookId);
    	boolean deleteBook=  bookservice.deleteBookById(book);
    	ResponseDTO responceDto = new ResponseDTO();
    	if (deleteBook) {
    		responceDto.setStatusCode("200");
    		responceDto.setStatusMessage("Book Deleted Successfully.");
    	} else {
    		responceDto.setStatusCode("500");
    		responceDto.setStatusMessage("Book not exists to Delete for the ID."+bookId);
    	}
    	return responceDto;
    }	
}
