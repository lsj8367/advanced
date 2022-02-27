package hello.proxy.configuration.v1proxy.concrete_proxy;

import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(final OrderServiceV2 target, final LogTrace logTrace) {
        super(null); //상위 기능을 쓸 필요가 없기 때문에 null할당
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(final String itemId) {
        TraceStatus status = null;

        try {
            status = logTrace.begin("OrderService.request()");
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
