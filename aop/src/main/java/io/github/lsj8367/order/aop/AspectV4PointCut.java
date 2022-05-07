package io.github.lsj8367.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV4PointCut {

    @Around("io.github.lsj8367.order.aop.PointCuts.allOrder()")
    public Object doLog(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //aop가 발생된 부분의 메소드명을 출력
        return joinPoint.proceed();
    }

    //io.github.lsj8367 패키지와 하위 패키지 이면서 Service로 끝나는 애들
    @Around("io.github.lsj8367.order.aop.PointCuts.allOrder() && io.github.lsj8367.order.aop.AspectV3.allService()")
    public Object doTransaction(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
