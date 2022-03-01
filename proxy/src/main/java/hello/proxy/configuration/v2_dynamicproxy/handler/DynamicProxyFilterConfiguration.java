package hello.proxy.configuration.v2_dynamicproxy.handler;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicProxyFilterConfiguration {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderControllerV1(final LogTrace logTrace) {
        final OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
            new Class[]{OrderControllerV1.class}, new LogTraceFilterHandler(orderController, logTrace, PATTERNS));
    }

    @Bean
    public OrderServiceV1 orderServiceV1(final LogTrace logTrace) {
        final OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
            new Class[]{OrderServiceV1.class}, new LogTraceFilterHandler(orderService, logTrace, PATTERNS));
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
        final OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
            new Class[]{OrderRepositoryV1.class}, new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS));
    }

}
