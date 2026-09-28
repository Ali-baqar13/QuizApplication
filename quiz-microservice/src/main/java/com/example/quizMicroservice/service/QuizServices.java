package com.example.quizMicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizMicroservice.feign.QuizInterface;
import com.example.quizMicroservice.model.QuestionWrapper;
import com.example.quizMicroservice.model.Quiz;
import com.example.quizMicroservice.repository.QuizRepo;



@Service
public class QuizServices {
    @Autowired
    public QuizRepo quizDao;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> creatQuiz(String title, int numQ, String category) {

        List<Integer> questions = quizInterface.getQuestionsByCategory(category,numQ).getBody();
        Quiz quiz = Quiz.builder().quizTitle(title).questionIds(questions).build();
        quizDao.save(quiz);
        System.out.println("quiz created with id: " + quiz.getId());
        
        return new ResponseEntity<>("created" , HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // there must be que=estion Wrapper
        List<Integer> QuestionIds = quiz.get().getQuestionIds();

        System.out.println("quiz: " + quiz);
        List<QuestionWrapper> QuestionFromDb = quizInterface.getQuestionById(QuestionIds).getBody();
        // List<QuestionWrapper> QuestionsForUser = new ArrayList<>();
        
        


        // for (Question q : QuestionFromDb) {
        //     QuestionWrapper qWrapper = new QuestionWrapper(q.getId(), q.getTitle(), q.getOptions1(), q.getOptions2(),
        //             q.getOptions3(), q.getOptions4());
        //     QuestionsForUser.add(qWrapper);
        // }
        
        return new ResponseEntity<>(QuestionFromDb, HttpStatus.OK);
    }

    // public ResponseEntity<Integer> getScore(int id, List<Response> response) {

    //     Optional<Quiz> quiz = quizDao.findById(id);
    //     if (quiz.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     if (response == null) {
    //         return ResponseEntity.badRequest().build();
    //     }
    //     List<Question> questions = quiz.get().getQuestions();
    //     Set<Integer> answeredIds = new HashSet<>();
    //     int count = 0;
    //     for (Response r : response) {
    //         if (r == null || r.getResponse() == null || !answeredIds.add(r.getId())) {
    //             return ResponseEntity.badRequest().build();
    //         }
    //         Optional<Question> question = questions.stream()
    //                 .filter(q -> q.getId() == r.getId()).findFirst();
    //         if (question.isEmpty()) {
    //             return ResponseEntity.badRequest().build();
    //         }
    //         if (r.getResponse().equals(question.get().getRightAnswer())) {
    //             count++;
    //         }
    //     }

    //     return new ResponseEntity<>(count, HttpStatus.OK);

    // }

}
