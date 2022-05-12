package io.github.lsj8367.aop.exam.internalholl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    //세터주입으로 자기 자신을 주입받게 변경
    @Autowired
    public void setCallServiceV1(final CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        //this.internal()로 내부호출을 하고 있기 때문에 프록시를 타서 수행되는것이 아님
        //그래서 aop를 동작하지 않고 바로 메소드만 호출하게 된다.
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
