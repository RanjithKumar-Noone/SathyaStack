package com.example.sathyastack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sathyastack.model.Book;
import com.example.sathyastack.service.BookService;

@Controller
@ResponseBody
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(path = "/getBooks", method = RequestMethod.GET)
	private ResponseEntity<List<Book>> getBooks(){
		List<Book> books = bookService.getBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	

	
	@GetMapping("/getBook/{id}")
	private ResponseEntity<Book> getBook(@PathVariable Long id){
		Book book = bookService.getBook(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@PostMapping("/saveBook/{userId}")
	private ResponseEntity<String> saveBook(@PathVariable Long userId, @RequestBody Book book){
		Book bookNew = bookService.saveBook(userId, book);
		if(bookNew != null) return new ResponseEntity<String>("h", HttpStatus.CREATED);
		else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/updateBook/{id}")
	private ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book){
	
		String response = bookService.updateBook(id, book);
		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteBook/{id}")
	private ResponseEntity<String> deleteBook(@PathVariable Long id){
		String response = bookService.deleteBook(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	
	
}
