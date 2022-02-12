package io.github.lsj8367.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(final String name) {
        log.info("저장 name={} -> nameStore = {}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore = {}", nameStore.get());
        return nameStore.get();
    }

    private void sleep(final int millIs) {
        try {
            Thread.sleep(millIs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
