package io.github.lsj8367.aop.exam.internalholl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        //this.internal()로 내부호출을 하고 있기 때문에 프록시를 타서 수행되는것이 아님
        //그래서 aop를 동작하지 않고 바로 메소드만 호출하게 된다.
        internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
