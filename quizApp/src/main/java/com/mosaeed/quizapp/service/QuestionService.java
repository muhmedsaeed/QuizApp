package com.mosaeed.quizapp.service;

import com.mosaeed.quizapp.dao.QuestionRepository;
import com.mosaeed.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;

    }


    // get all
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return ResponseEntity.ok(questionRepository.findAll());
            // return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    // get by id
    public ResponseEntity<Optional<Question>> getQuestionById(int id) {
        try {
            return new ResponseEntity<>(questionRepository.findById(id), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

    }

    // get by category
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    // get by difficulty level
    public ResponseEntity<List<Question>> getQuestionsByDifficultyLevel(String level) {
        try{
            return new ResponseEntity<>(questionRepository.findByDifficultyLevel(level), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    // add question
    public ResponseEntity<Question> addQuestion(Question question) {
        try{
            return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
        }
    }



    // update
    public ResponseEntity<Question> updateQuestion(Question question) {
        try {
            questionRepository.save(question);
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
        }
    }


    // delete
    public ResponseEntity<String> deleteQuestion(int id) {
        try{
            Optional<Question> questionOptional = questionRepository.findById(id);
            questionRepository.delete(questionOptional.get());
            return new ResponseEntity<>("Succesfull Deleting", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed To Delete", HttpStatus.BAD_REQUEST);
        }
    }










}










