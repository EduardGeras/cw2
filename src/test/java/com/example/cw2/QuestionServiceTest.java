package com.example.cw2;

import com.example.cw2.exception.QuestionAlreadyExistsException;
import com.example.cw2.exception.QuestionNotFoundException;
import com.example.cw2.model.Question;
import com.example.cw2.service.QuestionService;
import com.example.cw2.service.impl.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        // Удаляем все вопросы перед каждым тестом
        // questionService.getAll().forEach(question -> questionService.remove(question));
        // можно так и этак
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addRemoveTest() {
        Question question = new Question("Вопрос", "Ответ");

        // addPositiveTest()
        // Добавляем question
        questionService.add(question);
        // Проверяем, что в списке есть добавленный question
        assertThat(questionService.getAll().contains(question));

        // addNegativeTest()
        // Проверяем, что выбрасывается исключение, т.к. такой question уже существует
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add("Вопрос", "Ответ"));

        // removePositiveTest()
        questionService.remove(question);
        // Проверяем, что в списке есть добавленный question
        assertThat(questionService.getAll().isEmpty());

        // removeNegativeTest()
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question));
    }

    @Test
    public void getRandomQuestionTest() {
        Question question1 = new Question("Вопрос1", "Ответ1");
        Question question2 = new Question("Вопрос2", "Ответ2");
        Question question3 = new Question("Вопрос3", "Ответ3");
        questionService.add(question1);
        questionService.add(question2);
        questionService.add(question3);

        // Проверяем, что случайный вопрос есть в getAll()
        assertThat(questionService.getAll().contains(questionService.getRandomQuestion()));
    }

}
