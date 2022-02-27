package hello.proxy.configuration.v1proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.configuration.v1proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.configuration.v1proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.configuration.v1proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfiguration {

    @Bean
    public OrderControllerV2 orderControllerV2(final LogTrace logTrace) {
        return new OrderControllerConcreteProxy(new OrderControllerV2(orderServiceV2(logTrace)), logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(final LogTrace logTrace) {
        return new OrderServiceConcreteProxy(new OrderServiceV2(orderRepositoryV2(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(final LogTrace logTrace) {
        OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repositoryImpl, logTrace);
    }

}
