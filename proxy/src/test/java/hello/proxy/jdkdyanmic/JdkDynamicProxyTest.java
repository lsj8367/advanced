package hello.proxy.jdkdyanmic;

import hello.proxy.jdkdyanmic.code.AInterface;
import hello.proxy.jdkdyanmic.code.AInterfaceImpl;
import hello.proxy.jdkdyanmic.code.BInterface;
import hello.proxy.jdkdyanmic.code.BInterfaceImpl;
import hello.proxy.jdkdyanmic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AInterfaceImpl();

        final TimeInvocationHandler handler = new TimeInvocationHandler(target);

        final AInterface proxy = (AInterface) Proxy.newProxyInstance(
            AInterface.class.getClassLoader(),
            new Class[]{AInterface.class},
            handler);

        final String result = proxy.call();
        Assertions.assertThat(result).isEqualTo("a");
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BInterfaceImpl();

        final TimeInvocationHandler handler = new TimeInvocationHandler(target);

        final BInterface proxy = (BInterface) Proxy.newProxyInstance(
            BInterface.class.getClassLoader(),
            new Class[]{BInterface.class},
            handler);

        final String result = proxy.call();
        Assertions.assertThat(result).isEqualTo("b");
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
    }
}
