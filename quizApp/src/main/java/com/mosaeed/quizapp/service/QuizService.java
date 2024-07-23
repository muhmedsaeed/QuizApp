package com.mosaeed.quizapp.service;

import com.mosaeed.quizapp.dao.QuestionRepository;
import com.mosaeed.quizapp.dao.QuizRepository;
import com.mosaeed.quizapp.model.Question;
import com.mosaeed.quizapp.model.QuestionWrapper;
import com.mosaeed.quizapp.model.Quiz;
import com.mosaeed.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quizOptional = quizRepository.findById(id);
        List<Question> questionsFromDB = quizOptional.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(),
                    q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());

            questionsForUser.add(questionWrapper);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz = quizRepository.findById(id).get();
        int score = 0;
        int i = 0;
        for (Question q : quiz.getQuestions()) {
            if (q.getRightAnswer().equals(responses.get(i).getRightAnswer())) {
                score++;
            }
            i++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }


}
