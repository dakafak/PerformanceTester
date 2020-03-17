package dev.fanger.performancetester.output;

public class PrintOutput implements Output {

    @Override
    public void write(String string) {
        System.out.println(string);
    }

}
