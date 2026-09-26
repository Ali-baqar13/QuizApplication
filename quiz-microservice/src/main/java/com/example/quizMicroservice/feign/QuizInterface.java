package com.example.quizMicroservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizMicroservice.model.QuestionWrapper;
import com.example.quizMicroservice.model.Response;

@FeignClient("Question-Service")
public interface QuizInterface {
    @GetMapping ("generate")
    public ResponseEntity<List<Integer>> getQuestionsByCategory(@RequestParam String categoryName, @RequestParam int numberOfQuestions) ;
    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByIds(@RequestBody List<Integer> questionIds) ;

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(List<Response> responses);
}

