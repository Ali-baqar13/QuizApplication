package com.microservices.microservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservice.model.Question;
import com.microservices.microservice.model.QuestionWrapper;
import com.microservices.microservice.model.Response;
import com.microservices.microservice.repository.QuestionRepo;
import com.microservices.microservice.service.QuestionService;



@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    public QuestionService questionService;
    @Autowired
    public QuestionRepo questionRepository;

    // @GetMapping("get")
    // public ResponseEntity<List<Question>> getQuestions() {

    //     List<Question> questions = questionService.getQuestions();

    //     return new ResponseEntity<>(questions, HttpStatus.OK);
    // }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsByCategory(@RequestParam String categoryName, @RequestParam int numberOfQuestions) {
        return questionService.getQuestionRandomCategory(categoryName, numberOfQuestions);
    }
    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByIds(@RequestBody List<Integer> questionIds) {

        return questionService.getQuestionById(questionIds);

       

    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for (Response response : responses) {
            Question question = questionRepository.findById(response.getId()).get();
            if (response.getResponse().equals(question.getRightAnswer())) {
                score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
    

    // @PostMapping("create-question")
    // public ResponseEntity<String> postQuestions(@RequestBody Question question) {

    //     questionService.addQuestion(question);

    //     return new ResponseEntity<>("success", HttpStatus.CREATED);

    // }
   
}
