package com.example.quizMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizMicroservice.Dto.QuizDto;
import com.example.quizMicroservice.model.QuestionWrapper;
import com.example.quizMicroservice.model.Response;
import com.example.quizMicroservice.service.QuizServices;



@RequestMapping("quiz")
@RestController
public class QuizController {
    @Autowired

    public QuizServices quizService;
    

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {


        return quizService.creatQuiz(quizDto.getTitle(), quizDto.getNumQ(), quizDto.getCategory());
    }

    // @GetMapping("get-quiz/{id}")
    // public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
    //     return quizService.getQuizQuestions(id);

    // }

    // @PostMapping("validate/{id}")
    // public ResponseEntity<Integer> validateCount(@PathVariable int id, @RequestBody List<Response> response) {
    //     return quizService.getScore(id, response);
    // }
    
    
}
