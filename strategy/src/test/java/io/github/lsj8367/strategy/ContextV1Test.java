package io.github.lsj8367.strategy;

import io.github.lsj8367.strategy.code.ContextV1;
import io.github.lsj8367.strategy.code.Strategy;
import io.github.lsj8367.strategy.code.StrategyLogic1;
import io.github.lsj8367.strategy.code.StrategyLogic2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        System.out.println("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        System.out.println("resultTime= " + resultTime);
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        System.out.println("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        System.out.println("resultTime= " + resultTime);
    }

    @Test
    @DisplayName("명시적으로 생성해서 실행시키기")
    void strategyV1() {
        ContextV1 contextV1 = new ContextV1(new StrategyLogic1());
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new StrategyLogic2());
        contextV2.execute();
    }

    @Test
    @DisplayName("람다로 사용하기")
    void strategyV1Inner() {
        ContextV1 contextV1 = new ContextV1(() -> System.out.println("비즈니스 로직 실행"));
        contextV1.execute();
    }
}
