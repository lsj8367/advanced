package hello.proxy.configuration.v1proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.configuration.v1proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.configuration.v1proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.configuration.v1proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceConfiguration {

    @Bean
    public OrderControllerV1 orderController(final LogTrace logTrace) {
        return new OrderControllerInterfaceProxy(new OrderControllerV1Impl(orderService(logTrace)), logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(final LogTrace logTrace) {
        return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepository(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(final LogTrace logTrace) {
        return new OrderRepositoryInterfaceProxy(new OrderRepositoryV1Impl(), logTrace);
    }

}
