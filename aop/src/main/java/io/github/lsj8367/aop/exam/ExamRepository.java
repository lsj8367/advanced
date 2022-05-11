package io.github.lsj8367.aop.exam;

import io.github.lsj8367.aop.exam.annotation.Retry;
import io.github.lsj8367.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    @Trace
    @Retry(4)
    public String save(final String itemId) {
        seq++;

        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }

        return "ok";
    }

}
