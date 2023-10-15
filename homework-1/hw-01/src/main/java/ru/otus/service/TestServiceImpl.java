package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dao.QuestionDao;
import ru.otus.exceptions.QuestionReadException;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        try {
            ioService.printLine("");
            ioService.printFormattedLine("Please answer the questions below%n");
            int daoSizeArr = questionDao.findAll().size();
            for (int i = 0; i < daoSizeArr; i++) {
                ioService.printLine(questionDao.findAll().get(i).text());
                questionDao.findAll().get(i).answers().forEach(answer -> ioService.printLine(answer.text()));
            }
        } catch (QuestionReadException e) {
            throw new QuestionReadException("Test failed", e.fillInStackTrace());
        }
    }

}
