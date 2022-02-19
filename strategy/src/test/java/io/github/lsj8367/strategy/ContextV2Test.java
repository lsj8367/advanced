package io.github.lsj8367.strategy;

import io.github.lsj8367.strategy.code.ContextV2;
import io.github.lsj8367.strategy.code.StrategyLogic1;
import io.github.lsj8367.strategy.code.StrategyLogic2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContextV2Test {

    @Test
    @DisplayName("매개 변수로만 전략패턴 주입")
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    @DisplayName("익명 내부 클래스")
    void strategyUnknownV2() {
        ContextV2 context = new ContextV2();
        context.execute(() -> System.out.println("비즈니스 로직 실행"));
    }
}
