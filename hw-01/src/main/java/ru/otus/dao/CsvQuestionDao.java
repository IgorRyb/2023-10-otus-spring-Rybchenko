package ru.otus.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.config.TestFileNameProvider;
import ru.otus.dao.dto.QuestionDto;
import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        List<Question> allQuestions = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileNameProvider.getTestFileName());
             InputStreamReader streamReader = new InputStreamReader(is)) {
            List<QuestionDto> questionDtos = new CsvToBeanBuilder<QuestionDto>(streamReader)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .withType(QuestionDto.class)
                    .build().parse();
            for (int i = 0; i < questionDtos.size(); i++) {
                allQuestions.add(questionDtos.get(i).toDomainObject());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allQuestions;
    }


}
