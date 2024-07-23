package com.mosaeed.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data   /*  instead of getters and setters  */
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String difficultyLevel;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questionTitle;
    private String rightAnswer;


}
