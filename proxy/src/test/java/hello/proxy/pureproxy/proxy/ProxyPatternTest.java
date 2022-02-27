package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    @DisplayName("캐시 적용하지 않은 호출")
    void noProxyTest() {
        final RealSubject realSubject = new RealSubject();
        final ProxyPatternClient client = new ProxyPatternClient(realSubject);

        // 세번 호출하면 계속 서버 자원에 접근해서 가져오게 된다.
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    @DisplayName("캐시 적용한 프록시 호출")
    void cacheProxyTest() {
        final RealSubject realSubject = new RealSubject();
        final CacheProxy cacheProxy = new CacheProxy(realSubject);
        final ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
    }
}
