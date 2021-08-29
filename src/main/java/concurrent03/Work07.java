package concurrent03;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Syuan
 * @Date: 2021/8/29 10:46 下午
 */
@Slf4j
public class Work07 {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger sum = new AtomicInteger(0);
        Random random = new Random();
        Semaphore semaphore = new Semaphore(0);
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
            semaphore.release();
        });
        semaphore.acquire();
        log.info("[{}]", sum.get());
    }

}
