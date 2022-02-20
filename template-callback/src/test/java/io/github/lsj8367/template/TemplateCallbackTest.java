package io.github.lsj8367.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TemplateCallbackTest {

    @Test
    @DisplayName("익명 클래스 실행")
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직 1 실행");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직 2 실행");
            }
        });
    }

    @Test
    @DisplayName("람다로 실행")
    void callbackV2() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(() -> System.out.println("비즈니스 람다 로직 실행"));
    }
}
