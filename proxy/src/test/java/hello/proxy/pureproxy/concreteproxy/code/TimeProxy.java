package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic concreteLogic;

    public TimeProxy(final ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        log.info("TimeProxy 실행");
        final long startTime = System.currentTimeMillis();
        final String result = concreteLogic.operation();
        final long endTime = System.currentTimeMillis();

        final long resultTime = endTime - startTime;
        log.info("result Time = {}", resultTime);

        return result;
    }

}
