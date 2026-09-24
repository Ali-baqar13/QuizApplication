package com.microservices.microservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.microservice.model.Question;
import com.microservices.microservice.model.QuestionWrapper;
import com.microservices.microservice.repository.QuestionRepo;


/**
 * QuestionService
 */
@Service
public class QuestionService {

    private final QuestionRepo questionRepository;

    public QuestionService(QuestionRepo questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
    }
    // MicroServices tutorial starts here 
    public ResponseEntity<List<Integer>> getQuestionRandomCategory(String category, int numberOfQuestions) {
        return new ResponseEntity<>(questionRepository.findRandomQuestionsByCategory(category, numberOfQuestions), HttpStatus.OK);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuestionById(List<Integer> questionIds) {
        List<Question> questions = new ArrayList<>();
        List<QuestionWrapper> questionWrapper = new ArrayList<>();
        for (Integer id : questionIds) {
            questionRepository.findById(id).get();
            //  here i can get all question now i need to make wrappper for user

               
        }
        for(Question question : questions) {
             QuestionWrapper qw = QuestionWrapper.builder()
                .id(question.getId())
                .title(question.getTitle())
                .options1(question.getOptions1())
                .options2(question.getOptions2())
                .options3(question.getOptions3())
                .options4(question.getOptions4())
                .build();
            questionWrapper.add(qw);
        }
        return new ResponseEntity<>(questionWrapper, HttpStatus.OK);

        // for(QuestionWrapper question : questions) {

            
        // }
        // return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}