package concurrent03;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * @Author: Syuan
 * @Date: 2021/8/29 10:41 下午
 */
@Slf4j
public class Work05 {
    private static final AtomicInteger sum = new AtomicInteger(0);
    private static final AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            log.info("start");
            for (int i = 0; i < 10; ++i) {
                sum.addAndGet(i);
                try {
                    Thread.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag.set(true);
        });
        while(!flag.get()) {
            Thread.sleep(500);
        }
        log.info("[{}]", sum.get());
    }
}
