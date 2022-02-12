package io.github.lsj8367.configuration;

import io.github.lsj8367.trace.logtrace.FieldLogTrace;
import io.github.lsj8367.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }

}
