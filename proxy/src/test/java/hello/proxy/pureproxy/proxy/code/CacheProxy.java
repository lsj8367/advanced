package hello.proxy.pureproxy.proxy.code;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

    private Subject target; //프록시도 실제 객체를 호출해줘야 하기 때문에 가지고 있는다.
    private String cacheValue;

    public CacheProxy(final Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("캐시 프록시 호출");

        if (Objects.isNull(cacheValue)) {
            cacheValue = target.operation();
        }

        return cacheValue;
    }

}
