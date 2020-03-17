package dev.fanger.performancetester.tester;

import dev.fanger.performancetester.config.TestConfig;
import dev.fanger.performancetester.output.Output;

public class PerformanceTester implements Runnable {

    private String name;
    private TestConfig testConfig;
    private Output output;
    private long totalTestTimeInNanoseconds;

    public PerformanceTester(String name, TestConfig testConfig, Output output) {
        this.name = name;
        this.testConfig = testConfig;
        this.output = output;
    }

    public void run() {
        for(int i = 0; i < testConfig.getNumberOfTestsToRun(); i++) {
            output.write("Running performance tester: " + name);
            long startTime = System.nanoTime();
            testConfig.getRunnable().run();
            long endTime = System.nanoTime();
            totalTestTimeInNanoseconds += (endTime - startTime);
            output.write("Finished Running performance tester: " + name);
        }
    }

    public double getAverageTestTimeInNanoseconds() {
        return ((double) totalTestTimeInNanoseconds) / testConfig.getNumberOfTestsToRun();
    }

}
