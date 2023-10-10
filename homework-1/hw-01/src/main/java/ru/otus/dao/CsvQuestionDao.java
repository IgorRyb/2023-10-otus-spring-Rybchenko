package ru.otus.dao;

import com.opencsv.bean.CsvBindAndSplitByName;
import lombok.RequiredArgsConstructor;
import ru.otus.config.TestFileNameProvider;
import ru.otus.dao.dto.AnswerCsvConverter;
import ru.otus.domain.Question;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @CsvBindAndSplitByName(elementType = Question.class, splitOn = "\\|", converter = AnswerCsvConverter.class)
    private List<Question> allQuestions = new ArrayList<>();

    @Override
    public List<Question> findAll() {
        return new ArrayList<>();
    }
}
