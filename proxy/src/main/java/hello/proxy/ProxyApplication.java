package hello.proxy;

import hello.proxy.configuration.v3_proxyfactory.ProxyFactoryConfigV1;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Configuration.class)
//@Import({AppV1Configuration.class, AppV2Configuration.class}) //클래스를 스프링 빈으로 등록
//@Import(InterfaceConfiguration.class)
//@Import(ConcreteProxyConfiguration.class)
//@Import(DynamicProxyBasicConfiguration.class)
//@Import(DynamicProxyFilterConfiguration.class)
@Import(ProxyFactoryConfigV1.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
