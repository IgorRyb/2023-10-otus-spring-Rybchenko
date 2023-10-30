package ru.otus;

import  org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.TestRunnerService;

import java.io.IOException;

@PropertySource("classpath:application.properties")
@ComponentScan
@Configuration
public class Application {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        TestRunnerService testRunner = context.getBean(TestRunnerService.class);
        testRunner.run();
    }

}