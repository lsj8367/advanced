package hello.proxy.configuration.v4_postprocessor;

import hello.proxy.configuration.AppV1Configuration;
import hello.proxy.configuration.AppV2Configuration;
import hello.proxy.configuration.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.configuration.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Configuration.class, AppV2Configuration.class})
public class BeanPostProcessorConfiguration {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logTrace) {
        return new PackageLogTracePostProcessor("hello.proxy.app", getAdvisor(logTrace));
    }

    private Advisor getAdvisor(final LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

}
