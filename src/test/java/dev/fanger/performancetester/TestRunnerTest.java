package dev.fanger.performancetester;

import dev.fanger.performancetester.config.TestConfig;
import dev.fanger.performancetester.output.PrintOutput;
import org.junit.jupiter.api.Test;

public class TestRunnerTest {

    @Test
    public void testOneThread() {
        TestConfig testConfig = new TestConfig("TEST_CONFIG_ID", 1, 1, () -> {
           System.out.println("Hello I'm a test");
        });

        TestRunner testRunner = new TestRunner("TEST_RUNNER", testConfig, new PrintOutput());
        testRunner.run();

        System.out.println(testRunner.getTestResultString());
    }

    @Test
    public void testMultipleThreads() {
        TestConfig testConfig = new TestConfig("TEST_CONFIG_ID", 4, 1, () -> {
            System.out.println("Hello I'm a test");
        });

        TestRunner testRunner = new TestRunner("TEST_RUNNER", testConfig, new PrintOutput());
        testRunner.run();

        System.out.println(testRunner.getTestResultString());
    }

    @Test
    public void runManyTests() {
        TestConfig testConfig = new TestConfig("TEST_CONFIG_ID", 32, 4, () -> {
            System.out.println("Hello I'm a test");
        });

        TestRunner testRunner = new TestRunner("TEST_RUNNER", testConfig, new PrintOutput());
        testRunner.run();

        System.out.println(testRunner.getTestResultString());
    }

}
