package io.github.lsj8367.trace.hellotrace;

import io.github.lsj8367.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    @DisplayName("정상 시작 종료")
    void beginEnd() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);

    }

    @Test
    @DisplayName("정상 시작 후 예외 종료")
    void beginException() {
        HelloTraceV2 trace = new HelloTraceV2();
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}