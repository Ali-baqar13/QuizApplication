package com.example.quizMicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizMicroservice.model.Question;
import com.example.quizMicroservice.model.QuestionWrapper;
import com.example.quizMicroservice.model.Quiz;
import com.example.quizMicroservice.model.Response;
import com.example.quizMicroservice.repository.QuizRepo;



@Service
public class QuizServices {
    @Autowired
    public QuizRepo quizDao;
 

    public ResponseEntity<String> creatQuiz(String title, int numQ, String category) {

        Quiz quiz = new Quiz();
        // List<Question> randomQuestions = questionRepo.findRandomQuestionsByCategory(category, numQ);
        // quiz.setQuizTitle(title);
        // quiz.setQuestions(randomQuestions);
        // quizDao.save(quiz);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // there must be que=estion Wrapper
        List<Question> QuestionFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> QuestionsForUser = new ArrayList<>();

        for (Question q : QuestionFromDb) {
            QuestionWrapper qWrapper = new QuestionWrapper(q.getId(), q.getTitle(), q.getOptions1(), q.getOptions2(),
                    q.getOptions3(), q.getOptions4());
            QuestionsForUser.add(qWrapper);
        }

        return new ResponseEntity<>(QuestionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(int id, List<Response> response) {

        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Question> questions = quiz.get().getQuestions();
        Set<Integer> answeredIds = new HashSet<>();
        int count = 0;
        for (Response r : response) {
            if (r == null || r.getResponse() == null || !answeredIds.add(r.getId())) {
                return ResponseEntity.badRequest().build();
            }
            Optional<Question> question = questions.stream()
                    .filter(q -> q.getId() == r.getId()).findFirst();
            if (question.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (r.getResponse().equals(question.get().getRightAnswer())) {
                count++;
            }
        }

        return new ResponseEntity<>(count, HttpStatus.OK);

    }

}
