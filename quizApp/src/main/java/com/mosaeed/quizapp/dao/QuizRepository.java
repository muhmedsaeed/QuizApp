package com.mosaeed.quizapp.dao;

import com.mosaeed.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    // nothing to do


}
