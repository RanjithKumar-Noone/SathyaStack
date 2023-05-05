package com.example.sathyastack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sathyastack.model.Book;

public interface BookRepo extends JpaRepository<Book, Long>{

}
