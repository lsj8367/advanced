package hello.proxy.app;

public class OrderControllerV1Impl implements OrderControllerV1 {

    private final OrderServiceV1 orderService;

    public OrderControllerV1Impl(final OrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(final String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return null;
    }

}
