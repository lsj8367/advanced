package io.github.lsj8367.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

//    @Around("io.github.lsj8367.order.aop.PointCuts.allOrder() && io.github.lsj8367.order.aop.AspectV3.allService()")
//    public Object doTransaction(final ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            //@Before
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//
//            //@AfterReturning
//            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//            return result;
//        } catch (Exception e) {
//            //@AfterThrowing
//            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
//            throw e;
//        } finally {
//            //@After
//            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
//        }
//    }

    @Before("io.github.lsj8367.order.aop.PointCuts.allService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "io.github.lsj8367.order.aop.PointCuts.allService()", returning = "result")
    public void doReturn(final JoinPoint joinPoint, Object result) {
        //AfterReturning 에서는 반환 객체에 대해 데이터 핸들링을 할 수 없다.
        log.info("[return] {} result={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "io.github.lsj8367.order.aop.PointCuts.allService()", throwing = "ex")
    public void doThrow(final JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex);
        //자동으로 throw exception이 된다.
    }

    @After(value = "io.github.lsj8367.order.aop.PointCuts.allService()")
    public void doAfter(final JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
