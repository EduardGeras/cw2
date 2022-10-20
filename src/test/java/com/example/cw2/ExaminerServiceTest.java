package com.example.cw2;

import com.example.cw2.exception.IncorrectAmountOfQuestionException;
import com.example.cw2.model.Question;
import com.example.cw2.service.impl.ExaminerServiceImpl;
import com.example.cw2.service.impl.JavaQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void getQuestionPositiveTest() {
        Question question1 = new Question("Вопрос1", "Ответ1");
        Question question2 = new Question("Вопрос2", "Ответ2");
        Question question3 = new Question("Вопрос3", "Ответ3");
        Question question4 = new Question("Вопрос4", "Ответ4");
        Question question5 = new Question("Вопрос5", "Ответ5");

        Set<Question> allQuestions = Set.of(question1, question2, question3, question4, question5);
        when(javaQuestionService.getAll()).thenReturn(allQuestions);

        when(javaQuestionService.getRandomQuestion()).thenReturn(question2, question4, question1);
        assertThat(examinerService.getQuestions(3)).containsExactlyInAnyOrder(question2, question4, question1);

        when(javaQuestionService.getRandomQuestion())
                .thenReturn(question2, question4, question1, question2, question1, question3, question5);
        assertThat(examinerService.getQuestions(5))
                .containsExactlyInAnyOrder(question2, question4, question1, question3, question5);
    }

    @Test
    public void getQuestionNegativeTest() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptyList());

        assertThatExceptionOfType(IncorrectAmountOfQuestionException.class)
                .isThrownBy(() -> examinerService.getQuestions(5));

        assertThatExceptionOfType(IncorrectAmountOfQuestionException.class)
                .isThrownBy(() -> examinerService.getQuestions(-5));
    }
}
