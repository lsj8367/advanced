package io.github.lsj8367.trace.logtrace;

import io.github.lsj8367.trace.TraceId;
import io.github.lsj8367.trace.TraceStatus;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "✅--";
    private static final String EXCEPTION_PREFIX = "❌--";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(final String message) {
        syncTraceId();
        final TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        final TraceId traceId = traceIdHolder.get();
        if (Objects.isNull(traceId)) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextId());
        }

    }

    @Override
    public void end(final TraceStatus status) {
        complete(status, null);
    }

    @Override
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

        releaseTraceId();
    }

    private void releaseTraceId() {
        final TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove(); //destroy 스레드마다 각 값이 남아있을수 있기에 제거해준다.
        } else {
            traceIdHolder.set(traceId.createPreviousId());
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
