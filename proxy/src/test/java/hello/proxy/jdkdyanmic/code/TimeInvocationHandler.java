package hello.proxy.jdkdyanmic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(final Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        log.info("Time 프록시 실행");
        final long startTime = System.currentTimeMillis();

        final Object result = method.invoke(target, args);

        final long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("Time Proxy resultTime = {}", resultTime);
        return result;
    }

}
