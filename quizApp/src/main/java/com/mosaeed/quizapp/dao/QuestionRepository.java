package com.mosaeed.quizapp.dao;

import com.mosaeed.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // nothing to do
    // this is a magic of data jpa (JpaRepository)


    List<Question> findByCategory(String category);
    List<Question> findByDifficultyLevel(String difficultyLevel);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ",
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);



}
