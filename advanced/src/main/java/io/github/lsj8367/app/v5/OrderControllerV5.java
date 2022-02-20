package io.github.lsj8367.app.v5;

import io.github.lsj8367.trace.callback.TraceTemplate;
import io.github.lsj8367.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(final OrderServiceV5 orderService, final LogTrace logTrace) {
        this.orderService = orderService;
        // 이렇게 생성자에서 한번 주입 해주거나 아예 TraceTemplate를 빈으로 생성한다.
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(final String itemId) {

        return template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }

}
