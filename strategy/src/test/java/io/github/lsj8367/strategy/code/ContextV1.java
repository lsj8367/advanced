package io.github.lsj8367.strategy.code;

public class ContextV1 {

    private Strategy strategy;

    public ContextV1(final Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();

        strategy.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        System.out.println("resultTime= " + resultTime);
    }

}
