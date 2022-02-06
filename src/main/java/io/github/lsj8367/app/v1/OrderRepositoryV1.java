package io.github.lsj8367.app.v1;

import io.github.lsj8367.trace.TraceStatus;
import io.github.lsj8367.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(final String itemId) {

        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }

    private void sleep(final int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
