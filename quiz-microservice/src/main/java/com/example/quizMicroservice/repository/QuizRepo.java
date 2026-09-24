package com.example.quizMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizMicroservice.model.Quiz;



public interface QuizRepo extends JpaRepository<Quiz, Integer>{

     
    
}
