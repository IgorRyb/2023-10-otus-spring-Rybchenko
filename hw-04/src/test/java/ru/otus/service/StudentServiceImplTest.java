package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private LocalizedIOService ioService;

    private StudentService studentService;

    @BeforeEach
    void initEach() {
        studentService = new StudentServiceImpl(ioService);
    }

    @Test
    void checkFirstNameInput() {
        String expectedName = "Igor";
        Mockito.when(ioService.readStringWithPromptLocalized(anyString())).thenReturn("Igor");
        String actualName = studentService.determineCurrentStudent().firstName();
        Assertions.assertEquals(expectedName, actualName);
    }
}
