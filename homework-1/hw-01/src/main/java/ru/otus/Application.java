package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.config.AppConfig;
import ru.otus.config.TestFileNameProvider;
import ru.otus.dao.CsvQuestionDao;
import ru.otus.service.TestRunnerService;

import java.io.IOException;


public class Application {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestRunnerService testRunner = context.getBean(TestRunnerService.class);
        testRunner.run();
        TestFileNameProvider testFileNameProvider = new AppConfig("questions.csv");
        new CsvQuestionDao(testFileNameProvider).findAll();
    }

}