package com.example.sathyastack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sathyastack.model.User;
import com.example.sathyastack.repository.UserRepo;
import com.example.sathyastack.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/getUsers", method = RequestMethod.GET)
	private ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getUsers();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("getUser/{id}")
	private ResponseEntity<User> getUser(@PathVariable Long id){
		User user = userService.getUser(id);
		ObjectMapper obj = new ObjectMapper();
		String json = null;
		try {
			json = obj.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null) return ResponseEntity.ok(user);
		else return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/saveUser")
	private ResponseEntity<User> saveUser(@RequestBody User user){
		User newUser = userService.saveUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{id}")
	private ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){

		if (userService.updateUser(id, user)) return new ResponseEntity<>(HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	

	
}
