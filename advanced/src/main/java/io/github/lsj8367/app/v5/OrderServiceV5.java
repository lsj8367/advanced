package io.github.lsj8367.app.v5;

import io.github.lsj8367.trace.callback.TraceTemplate;
import io.github.lsj8367.trace.logtrace.LogTrace;
import io.github.lsj8367.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(final OrderRepositoryV5 orderRepository, final LogTrace logTrace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void orderItem(final String itemId) {
        traceTemplate.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
