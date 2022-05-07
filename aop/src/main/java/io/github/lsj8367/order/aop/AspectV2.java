package io.github.lsj8367.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    @Pointcut("execution(* io.github.lsj8367.order..*(..))")
    private void allOrder(){}

    @Around("allOrder()")
    public Object doLog(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //aop가 발생된 부분의 메소드명을 출력
        return joinPoint.proceed();
    }

}

