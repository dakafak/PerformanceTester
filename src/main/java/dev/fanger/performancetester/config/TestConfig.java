package dev.fanger.performancetester.config;

public class TestConfig {

    private String name;
    private int numberOfThreads;
    private int numberOfTestsToRun;
    private Runnable runnable;

    public TestConfig(String name, int numberOfThreads, int numberOfTestsToRun, Runnable runnable) {
        this.name = name;
        this.numberOfThreads = numberOfThreads;
        this.numberOfTestsToRun = numberOfTestsToRun;
        this.runnable = runnable;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public int getNumberOfTestsToRun() {
        return numberOfTestsToRun;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public String toString() {
        return "TestConfig{" + System.lineSeparator() +
                "\tname=" + name + "," + System.lineSeparator() +
                "\tnumberOfThreads=" + numberOfThreads + "," + System.lineSeparator() +
                "\tnumberOfTestsToRun=" + numberOfTestsToRun + System.lineSeparator() +
                '}';
    }

}
