package io.github.lsj8367.aop.exam.internalholl;

import io.github.lsj8367.aop.exam.internalholl.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(CallLogAspect.class)
class CallServiceV3Test {

    @Autowired
    private CallServiceV3 callServiceV3;

    @Test
    void external() {
        callServiceV3.external();
    }

}