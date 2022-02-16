package io.github.lsj8367.trace.logtrace;

import io.github.lsj8367.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(final String message);

    void end(final TraceStatus status);

    void exception(final TraceStatus status, final Exception e);

}
