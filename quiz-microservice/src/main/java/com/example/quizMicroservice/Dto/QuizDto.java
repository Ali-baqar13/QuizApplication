package com.example.quizMicroservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class QuizDto {

    private String title;
    private int numQ;
    private String category;

    
}
