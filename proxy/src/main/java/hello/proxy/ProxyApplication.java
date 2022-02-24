package hello.proxy;

import hello.proxy.configuration.AppV1Configuration;
import hello.proxy.configuration.AppV2Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import(AppV1Configuration.class)
@Import({AppV1Configuration.class, AppV2Configuration.class}) //클래스를 스프링 빈으로 등록
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
