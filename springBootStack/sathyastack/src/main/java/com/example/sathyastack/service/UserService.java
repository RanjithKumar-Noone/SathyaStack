package com.example.sathyastack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sathyastack.model.User;
import com.example.sathyastack.repository.UserRepo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	public User getUser(long id) {
		Optional<User> userFound = userRepo.findById(id);
		if(userFound.isPresent()) return userFound.get();
		return null;
	}
	
	public boolean updateUser(long id, User user) {
		Optional<User> userFound = userRepo.findById(id);
		if(userFound.isPresent()) {
			user.setUserId(userFound.get().getUserId());
			user.setBooks(userFound.get().getBooks());
			userRepo.save(user);
			return true;
			
		}else return false;
		
	}
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public boolean deleteUser(long id) {
		Optional<User> userFound = userRepo.findById(id);
		if(userFound.isPresent()) {
			userRepo.deleteById(id);
			return true;
		}else return false;
	}

}
