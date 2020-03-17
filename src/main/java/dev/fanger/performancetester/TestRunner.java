package dev.fanger.performancetester;

import dev.fanger.performancetester.config.TestConfig;
import dev.fanger.performancetester.output.Output;
import dev.fanger.performancetester.tester.PerformanceTester;

import java.util.ArrayList;
import java.util.List;

public class TestRunner implements Runnable {

    private String name;
    private TestConfig testConfig;
    private List<Thread> threads;
    private List<PerformanceTester> performanceTesters;
    private Output output;
    private long totalRunTimeInNanoseconds;

    public TestRunner(String name, TestConfig testConfig, Output output) {
        this.name = name;
        this.testConfig = testConfig;
        this.output = output;

        threads = new ArrayList<>();
        performanceTesters = new ArrayList<>();
    }

    public void run() {
        output.write("Creating test runner threads");

        for(int i = 1; i <= testConfig.getNumberOfThreads(); i++) {
            PerformanceTester performanceTester = new PerformanceTester("Tester_" + i, testConfig, output);
            Thread performanceTesterThread = new Thread(performanceTester);

            threads.add(performanceTesterThread);
            performanceTesters.add(performanceTester);

            output.write("Created performance tester threads " + i + "/" + testConfig.getNumberOfThreads());
        }

        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                continue;
            }
        }

        for(PerformanceTester performanceTester : performanceTesters) {
            totalRunTimeInNanoseconds += performanceTester.getAverageTestTimeInNanoseconds();
        }
    }

    public double getAverageRunTimeInNanoseconds() {
        return ((double) totalRunTimeInNanoseconds ) / testConfig.getNumberOfThreads();
    }

    public double getAverageRunTimeInMilliseconds() {
        return getAverageRunTimeInNanoseconds() / 1_000_000;
    }

    public double getAverageRunTimeInSeconds() {
        return getAverageRunTimeInMilliseconds() / 1_000;
    }

    public String getTestResultString() {
        String resultsTitle = "==== Test results for " + name + " ====";

        StringBuilder stringBuilder = new StringBuilder(System.lineSeparator());
        stringBuilder.append(resultsTitle);
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append(testConfig.toString());
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("Average run time:");
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("In nanoseconds: ");
        stringBuilder.append(getAverageRunTimeInNanoseconds());
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("In milliseconds: ");
        stringBuilder.append(getAverageRunTimeInMilliseconds());
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("In seconds: ");
        stringBuilder.append(getAverageRunTimeInSeconds());
        stringBuilder.append(System.lineSeparator());

        int resultsTitleLength = resultsTitle.length();
        String resultsEnd = "";
        for(int i = 0; i < resultsTitleLength; i++) {
            resultsEnd += "=";
        }

        stringBuilder.append(resultsEnd);
        return stringBuilder.toString();
    }

}
