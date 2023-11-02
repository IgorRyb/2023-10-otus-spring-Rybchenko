package ru.otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "test")
public class AppProps implements TestFileNameProvider, TestConfig, LocaleConfig {

    private final Locale locale;

    private final Map<String, String> fileNameByLocaleTag;

    private final int rightAnswersCountToPass;

    @ConstructorBinding
    public AppProps(Locale locale, Map<String, String> fileNameByLocaleTag, int rightAnswersCountToPass) {
        this.locale = locale;
        this.fileNameByLocaleTag = fileNameByLocaleTag;
        this.rightAnswersCountToPass = rightAnswersCountToPass;
    }

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getTestFileName() {
        return fileNameByLocaleTag.get(locale.toLanguageTag());
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
