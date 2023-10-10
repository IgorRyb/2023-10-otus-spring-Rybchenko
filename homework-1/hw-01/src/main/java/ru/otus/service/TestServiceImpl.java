package ru.otus.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.config.AppConfig;
import ru.otus.config.TestFileNameProvider;
import ru.otus.dao.dto.QuestionDto;
import ru.otus.exceptions.QuestionReadException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        TestFileNameProvider fileName = new AppConfig("D:\\git\\2023-10-otus-spring-Rybchenko\\" +
                "hw-01\\src\\main\\resources\\questions.csv");
        try {
            FileReader fileReader = new FileReader(fileName.getTestFileName());
            List<QuestionDto> questionDtos = new CsvToBeanBuilder(fileReader)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .withType(QuestionDto.class)
                    .build().parse();

            for (int i = 0; i < questionDtos.size(); i++) {
                ioService.printLine(questionDtos.get(i).getText());
                questionDtos.get(i).getAnswers().stream().forEach(answer -> ioService.printLine(answer.text()));
            }
        } catch (FileNotFoundException e) {
            throw new QuestionReadException("Test failed: ", e.fillInStackTrace());
        }
    }
}
