package io.github.lsj8367.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {


    @Pointcut("execution(* io.github.lsj8367.order..*(..))")
    public void allOrder() {
    }

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {
    }

}
