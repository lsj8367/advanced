package hello.proxy.jdkdyanmic;

import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @Test
    void reflectionTest() {
        Hello target = new Hello();
        // 공통 로직1 시작
        log.info("start");
        final String result1 = target.callA();

        assertThat(result1).isEqualTo("A");
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        final String result2 = target.callB();

        assertThat(result2).isEqualTo("B");
        // 공통 로직2 종료
    }

    @Test
    void reflectionTest2() throws Exception {
        // 클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdyanmic.ReflectionTest$Hello");

        Hello target = new Hello();
        //call A 메소드 정보

        final Method methodCallA = classHello.getMethod("callA");
        final Object result = dynamicCall(methodCallA, target);
        assertThat(result).isEqualTo("A");

        final Method methodCallB = classHello.getMethod("callB");
        final Object result2 = dynamicCall(methodCallB, target);
        assertThat(result2).isEqualTo("B");
    }

    private Object dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        return method.invoke(target);
    }

    @Slf4j
    private static class Hello {
        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call B");
            return "B";
        }
    }

}
