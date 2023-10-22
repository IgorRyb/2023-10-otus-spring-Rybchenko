package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements TestFileNameProvider, TestConfig {

    @Value("${test.correctAnswersForPassing}")
    private int rightAnswersCountToPass;

    @Value("${test.file.name}")
    private String testFileName;

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
