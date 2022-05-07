package io.github.lsj8367.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    public String save(final String itemId) {
        log.info("[orderRepository] 실행");
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }

        return "ok";
    }

}
