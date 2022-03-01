package hello.proxy.cglib.code;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(final Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy methodProxy) throws Throwable {
        log.info("Time 프록시 실행");
        final long startTime = System.currentTimeMillis();

        final Object result = methodProxy.invoke(target, args);

        final long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("Time Proxy resultTime = {}", resultTime);
        return result;
    }

}
