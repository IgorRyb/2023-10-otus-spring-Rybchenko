package ru.otus.dao;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.config.TestFileNameProvider;
import ru.otus.dao.dto.AnswerCsvConverter;
import ru.otus.dao.dto.QuestionDto;
import ru.otus.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @CsvBindAndSplitByName(elementType = Question.class, splitOn = "\\|", converter = AnswerCsvConverter.class)
    private List<Question> allQuestions = new ArrayList<>();

    @Override
    public List<Question> findAll() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileNameProvider.getTestFileName());
        InputStreamReader streamReader = new InputStreamReader(is);
        List<QuestionDto> questionDtos = new CsvToBeanBuilder(streamReader)
                .withSkipLines(1)
                .withSeparator(';')
                .withType(QuestionDto.class)
                .build().parse();
        for (int i = 0; i < questionDtos.size(); i++) {
            allQuestions.add(new Question(questionDtos.get(i).getText(), questionDtos.get(i).getAnswers()));
        }
        return allQuestions;
    }


}
