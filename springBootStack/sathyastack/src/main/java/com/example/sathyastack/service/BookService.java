package com.example.sathyastack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sathyastack.model.Book;
import com.example.sathyastack.model.User;
import com.example.sathyastack.repository.BookRepo;
import com.example.sathyastack.repository.UserRepo;


@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public List<Book> getBooks(){
		return bookRepo.findAll();
	}
	
	public Book getBook(long id) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isPresent()) return book.get();
		else return new Book();
	}
	
	public Book saveBook(long userId, Book book) {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			book.setUser(user.get());
			return bookRepo.save(book);
		}else return null;
		
	}
	
	public String updateBook(long id, Book book) {
		Optional<Book> bookFound = bookRepo.findById(id);
		if(bookFound.isPresent()) {
			book.setBookId(bookFound.get().getBookId());
			book.setUser(bookFound.get().getUser());
			return "Updation Success";
		}return "No Book found";
	}
	
	public String deleteBook(long id) {
		Optional<Book> bookFound = bookRepo.findById(id);
		if(bookFound.isPresent()) {
			bookRepo.deleteById(id);
			return "Book deleted";
		}else return "No Book Found";
	}
	
	
}
