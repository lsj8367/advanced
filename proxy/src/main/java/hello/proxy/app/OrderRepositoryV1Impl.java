package hello.proxy.app;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {

    @Override
    public void save(final String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }
        sleep(1000);
    }

    private void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
