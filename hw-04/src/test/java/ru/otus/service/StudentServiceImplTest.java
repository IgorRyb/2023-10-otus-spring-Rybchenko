package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class StudentServiceImplTest {

    @MockBean
    private LocalizedIOService ioService;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void initEach() {
        studentService = new StudentServiceImpl(ioService);
    }

    @DisplayName("Должен возвращать корректное отображение введенного имени")
    @Test
    void checkFirstNameInput() {
        String expectedName = "Igor";
        Mockito.when(ioService.readStringWithPromptLocalized(anyString())).thenReturn("Igor");
        String actualName = studentService.determineCurrentStudent().firstName();
        Assertions.assertEquals(expectedName, actualName);
    }
}
