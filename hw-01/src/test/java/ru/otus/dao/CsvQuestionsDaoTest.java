package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.config.TestFileNameProvider;

@ExtendWith(MockitoExtension.class)
public class CsvQuestionsDaoTest {

    @Mock
    private TestFileNameProvider testFileNameProvider;

    private QuestionDao questionDao;

    @BeforeEach
    void initEach() {
        questionDao = new CsvQuestionDao(testFileNameProvider);
    }

    @Test
    void checkSizeQuestionsArray() {
        int expectedArraySize = 5;
        Mockito.when(testFileNameProvider.getTestFileName()).thenReturn("questions.csv");
        int actualArraySize = questionDao.findAll().size();
        Assertions.assertEquals(expectedArraySize, actualArraySize);
    }
}
