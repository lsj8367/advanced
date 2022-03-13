package hello.proxy.postprocessor;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorConfigurationTest {

    @Test
    void basicConfiguration() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfiguration.class);

        //B는 b으로 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        Assertions.assertThatThrownBy(() -> applicationContext.getBean(A.class))
            .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Slf4j
    @Configuration
    @NoArgsConstructor // 기본생성자 명시해주지 않으면 에러 발생
    private static class BeanPostProcessorConfiguration {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AToBPostProcessor helloPostProcessor() {
            return new AToBPostProcessor();
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

    @Slf4j
    private static class AToBPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
            log.info("beanName={}, bean={}", beanName, bean);

            if (bean instanceof A) {
                return new B();
            }

            return bean;
        }

    }

}
