package io.github.lsj8367.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(final String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }

        sleep(1000);
    }

    private void sleep(final int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
