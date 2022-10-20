package com.example.cw2.service.impl;

import com.example.cw2.exception.IncorrectAmountOfQuestionException;
import com.example.cw2.model.Question;
import com.example.cw2.service.ExaminerService;
import com.example.cw2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 0 || amount > questionService.getAll().size()) {
            throw new IncorrectAmountOfQuestionException("Неверное количество вопросов");
        }
        // Set обеспечивает уникальность вопросов (например, List не обеспечит)
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}