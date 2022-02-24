package hello.proxy.configuration;

import hello.proxy.app.OrderControllerV1;
import hello.proxy.app.OrderControllerV1Impl;
import hello.proxy.app.OrderRepositoryV1;
import hello.proxy.app.OrderRepositoryV1Impl;
import hello.proxy.app.OrderServiceV1;
import hello.proxy.app.OrderServiceV1Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Configuration {

    @Bean
    public OrderControllerV1 orderControllerV1() {
        return new OrderControllerV1Impl(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceV1Impl(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryV1Impl();
    }

}
