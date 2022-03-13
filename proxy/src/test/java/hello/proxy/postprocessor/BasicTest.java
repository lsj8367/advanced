package hello.proxy.postprocessor;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfiguration() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfiguration.class);

        //A는 빈으로 등록된다.
        final A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        Assertions.assertThatThrownBy(() -> applicationContext.getBean(B.class))
            .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Slf4j
    @Configuration
    @NoArgsConstructor // 기본생성자 명시해주지 않으면 에러 발생
    private static class BasicConfiguration {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    private static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    private static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
