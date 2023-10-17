package ru.otus.service;

import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;

@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {

    private final TestService testService;

    @Override
    public void run() throws FileNotFoundException {
        testService.executeTest();
    }
}
