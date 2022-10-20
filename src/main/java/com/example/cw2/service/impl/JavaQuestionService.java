package com.example.cw2.service.impl;

import com.example.cw2.exception.QuestionAlreadyExistsException;
import com.example.cw2.exception.QuestionNotFoundException;
import com.example.cw2.model.Question;
import com.example.cw2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistsException("Вопрос уже существует");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException("Вопрос не найден");
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        // Вернуть нужно не оригинал, а копию
        // Иначе смогут изменить (стереть) оригинал
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        // Создали список и получили get по индексу
        // Индекс случайное число от 0 до questions.size() (размер Set)
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}
