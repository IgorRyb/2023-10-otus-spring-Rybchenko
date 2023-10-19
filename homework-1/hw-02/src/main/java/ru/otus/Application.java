package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.TestRunnerService;

import java.io.IOException;

@ComponentScan
public class Application {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        TestRunnerService testRunner = context.getBean(TestRunnerService.class);
        testRunner.run();
    }

}