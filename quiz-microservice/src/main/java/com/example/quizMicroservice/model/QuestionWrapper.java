package com.example.quizMicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class QuestionWrapper {

    private int id;
    private String title;
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    
}
