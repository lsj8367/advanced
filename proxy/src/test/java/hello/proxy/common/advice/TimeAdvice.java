package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        log.info("Time 프록시 실행");
        final long startTime = System.currentTimeMillis();

        // target 클래스 호출, 결과 받음
        final Object result = invocation.proceed();

        final long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("Time Proxy resultTime = {}", resultTime);
        return result;
    }

}
