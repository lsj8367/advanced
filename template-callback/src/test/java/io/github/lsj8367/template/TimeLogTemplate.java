package io.github.lsj8367.template;

public class TimeLogTemplate {

    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();

        callback.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        System.out.println("resultTime = " + resultTime);
    }
}
