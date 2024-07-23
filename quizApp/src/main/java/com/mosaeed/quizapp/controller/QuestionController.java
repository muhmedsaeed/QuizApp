package com.mosaeed.quizapp.controller;

import com.mosaeed.quizapp.model.Question;
import com.mosaeed.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable int id) {

        return questionService.getQuestionById(id);
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("cat") String category) {

        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/difficultyLevel/{level}")
    public ResponseEntity<List<Question>> getQuestionsByDifficultyLevel(@PathVariable String level) {

        return questionService.getQuestionsByDifficultyLevel(level);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {

        return questionService.addQuestion(question);
    }


    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {

        return questionService.updateQuestion(question);
    }


    @PatchMapping("/patch")
    public ResponseEntity<Question> updateQuestionPatch(@RequestBody Question question) {

        return questionService.updateQuestion(question);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {

        return questionService.deleteQuestion(id);
    }




}
