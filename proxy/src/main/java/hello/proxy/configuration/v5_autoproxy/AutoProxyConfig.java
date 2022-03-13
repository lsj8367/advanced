package hello.proxy.configuration.v5_autoproxy;

import hello.proxy.configuration.AppV1Configuration;
import hello.proxy.configuration.AppV2Configuration;
import hello.proxy.configuration.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Configuration.class, AppV2Configuration.class})
public class AutoProxyConfig {

//    @Bean
//    public Advisor advisor1(final LogTrace logTrace) {
//        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
//        pointcut.setMappedNames("request*", "order*", "save*");
//
//        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
//
//        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
//    }

//    @Bean
//    public Advisor advisor2(final LogTrace logTrace) {
//        //pointcut
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution(* hello.proxy.app..*(..))");
//
//        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
//        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
//    }

    @Bean
    public Advisor advisor3(final LogTrace logTrace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");

        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

}
