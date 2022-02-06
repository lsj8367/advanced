package io.github.lsj8367.trace.hellotrace;

import io.github.lsj8367.trace.TraceId;
import io.github.lsj8367.trace.TraceStatus;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "--✅";
    private static final String EXCEPTION_PREFIX = "--❌";

    public TraceStatus begin(final String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    public void end(final TraceStatus status) {
        complete(status, null);
    }

    public void exception(final TraceStatus status, final Exception e) {
        complete(status, e);
    }

    private void complete(final TraceStatus status, final Exception e) {
        final Long stopTimeMs = System.currentTimeMillis();
        final long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        final TraceId traceId = status.getTraceId();

        if (Objects.isNull(e)) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EXCEPTION_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }

    private static String addSpace(final String prefix, int level) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }

}
