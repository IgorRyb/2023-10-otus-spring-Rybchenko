package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.service.LocalizedIOService;
import ru.otus.service.TestRunnerService;

@RequiredArgsConstructor
@ShellComponent
public class ApplicationCommands {

    private final TestRunnerService testRunner;

    private final LocalizedIOService ioService;

    private int age;

    @ShellMethod(value = "Test run command", key = {"r", "run"})
    @ShellMethodAvailability("isCheckAgeAvailable")
    public void run() {
        testRunner.run();
    }

    @ShellMethod(value = "Check age command", key = {"a", "age"})
    public void checkAge(int age) {
        this.age = age;
        ioService.printFormattedLineLocalized("ApplicationCommands.output.age", age);
    }

    public Availability isCheckAgeAvailable() {
        return age == 0 ? Availability.unavailable(ioService.getMessage("ApplicationCommands.fail.input.text"))
                : Availability.available();
    }
}
