package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.config.TestFileNameProvider;

@ExtendWith(MockitoExtension.class)
public class CsvQuestionsDaoTest {

    @Mock
    private TestFileNameProvider testFileNameProvider;

    @InjectMocks
    private CsvQuestionDao csvQuestionDao;

    @Test
    void checkSizeQuestionsArray() {
        int expectedArraySize = 5;
        Mockito.when(testFileNameProvider.getTestFileName()).thenReturn("questions.csv");
        int actualArraySize = csvQuestionDao.findAll().size();
        Assertions.assertEquals(expectedArraySize, actualArraySize);
    }
}
