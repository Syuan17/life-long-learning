package concurrent03;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * @Author: Syuan
 * @Date: 2021/8/29 10:46 下午
 */
@Slf4j
public class Work06 {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger sum = new AtomicInteger(0);
        Random random = new Random();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            log.info("start");
            for (int i = 0; i < 10; ++i) {
                sum.addAndGet(i);
                try {
                    Thread.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
        log.info("[{}]", sum.get());
    }

}
