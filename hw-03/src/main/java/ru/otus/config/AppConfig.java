package ru.otus.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Getter @Setter
@Component
public class AppConfig implements TestFileNameProvider, TestConfig, LocaleConfig {

    private Locale locale;

    private Map<String, String> fileNameByLocaleTag;

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

    public void setLocale(String locale) {
        this.locale = Locale.forLanguageTag(locale);
    }
}
