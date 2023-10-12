package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dao.CsvQuestionDao;
import ru.otus.exceptions.QuestionReadException;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final CsvQuestionDao csvQuestionDao;

    @Override
    public void executeTest() {
        try {
            ioService.printLine("");
            ioService.printFormattedLine("Please answer the questions below%n");
            int daoSizeArr = csvQuestionDao.findAll().size();
            for (int i = 0; i < daoSizeArr; i++) {
                ioService.printLine(csvQuestionDao.findAll().get(i).text());
                csvQuestionDao.findAll().get(i).answers()
                        .stream()
                        .forEach(answer -> ioService.printLine(answer.text()));
            }
        } catch (QuestionReadException e) {
            throw new QuestionReadException("Test failed", e.fillInStackTrace());
        }
    }

}
