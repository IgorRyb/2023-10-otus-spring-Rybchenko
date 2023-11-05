package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@RequiredArgsConstructor
@ShellComponent
public class ShellRunnerServiceImpl implements ShellRunnerService {

    private final TestRunnerService testRunner;

    private final LocalizedIOService ioService;

    private String age;

    @ShellMethod(value = "Test run command", key = {"r", "run"})
    @ShellMethodAvailability("isCheckAgeAvailable")
    @Override
    public void run() {
        testRunner.run();
    }

    @ShellMethod(value = "Check age command", key = {"a", "age"})
    @Override
    public void checkAge() {
        age = ioService.readStringWithPromptLocalized("ShellRunnerService.input.age");
        ioService.printFormattedLineLocalized("ShellRunnerService.output.age", age);
    }

    public Availability isCheckAgeAvailable() {
        return age == null? Availability.unavailable(ioService.getMessage("ShellRunnerService.fail.input.text")) : Availability.available();
    }
}
