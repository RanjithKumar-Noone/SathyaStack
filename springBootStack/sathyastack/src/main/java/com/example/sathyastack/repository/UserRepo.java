package com.example.sathyastack.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sathyastack.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	

}
