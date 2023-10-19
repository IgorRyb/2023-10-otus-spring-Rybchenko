package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.Student;
import ru.otus.domain.TestResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = askAllQuestions(student, questions);
        return testResult;
    }

    public TestResult askAllQuestions(Student student, List<Question> questions) {
        var testResult = new TestResult(student);
        for (int i = 0; i < questions.size(); i++) {
            printQuestionAndAnswer(i);
            String studentResponse = ioService.readString();
            String trueAnswer = getTrueAnswer(questions.get(i).answers());
            var isAnswerValid = comparingEnteredAndCorrectAnswers(questions.get(i).answers(),
                    studentResponse, trueAnswer);
            testResult.applyAnswer(questions.get(i), isAnswerValid);
        }
        return testResult;
    }

    public void printQuestionAndAnswer(int i) {
        ioService.printLine(questionDao.findAll().get(i).text());
        questionDao.findAll().get(i).answers().forEach(answer -> ioService.printLine(answer.text()));
    }

    public String getTrueAnswer(List<Answer> answers) {
        String trueAnswer = null;
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                trueAnswer = answer.text();
            }
        }
        return trueAnswer;
    }

    public boolean comparingEnteredAndCorrectAnswers(List<Answer> answers, String studentResponse, String trueAnswer) {
        boolean isAnswerValid = false;
        for (int i = 0; i < answers.size(); i++) {
            if (studentResponse.equals(trueAnswer)) {
                isAnswerValid = true;
            }
        }
        return isAnswerValid;
    }
}