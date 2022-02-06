package io.github.lsj8367.trace.hellotrace;

import io.github.lsj8367.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @Test
    @DisplayName("정상 시작 종료")
    void beginEnd() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    @DisplayName("정상 시작 후 예외 종료")
    void beginException() {
        HelloTraceV1 trace = new HelloTraceV1();
        final TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}