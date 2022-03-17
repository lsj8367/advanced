package hello.proxy.configuration.v6_aop.aspect;

import hello.proxy.configuration.AppV1Configuration;
import hello.proxy.configuration.AppV2Configuration;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Configuration.class, AppV2Configuration.class})
public class AopConfiguration {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }

}
