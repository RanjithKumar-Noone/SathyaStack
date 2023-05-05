package com.example.sathyastack.service;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sathyastack.model.Book;
import com.example.sathyastack.model.Order;
import com.example.sathyastack.model.User;
import com.example.sathyastack.repository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	public String saveOrder(long userId, long bookId) {
		Optional<Order> orderFound = 
				orderRepo.findByUserUserIdAndOrderStatus(userId, false);
		Book book  = bookService.getBook(bookId);
		if(orderFound.isPresent()) {
			Order order = orderFound.get();
			order.getBooks().add(book);
			orderRepo.save(order);
		}else {
			User user = userService.getUser(bookId);
			Order order = new Order();
			order.setUser(user);
			order.setBooks(new LinkedList<>());
			order.getBooks().add(book);
			orderRepo.save(order);
		}
		return "Success";
				
	}
}
