package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.config.TestFileNameProvider;

@SpringBootTest(classes = CsvQuestionDao.class)
public class CsvQuestionsDaoTest {

    @MockBean
    private TestFileNameProvider testFileNameProvider;

    @Autowired
    private QuestionDao questionDao;

    @DisplayName("Должен возвращать корректное количество вопросов в тесте")
    @Test
    void checkSizeQuestionsArray() {
        int expectedArraySize = 5;
        Mockito.when(testFileNameProvider.getTestFileName()).thenReturn("questions.csv");
        int actualArraySize = questionDao.findAll().size();
        Assertions.assertEquals(expectedArraySize, actualArraySize);
    }
}
