package concurrent03;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Syuan
 * @Date: 2021/8/29 10:23 下午
 */
@Slf4j
public class Work04 {
    private static final List<Integer> list = new ArrayList<>(1);

    private static final Integer FIRST = 0;

    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(1);
        executor.submit(() -> {
            int sum = 0;
            log.info("start");
            for (int i = 0; i < 10; ++i) {
                sum += i;
                try {
                    Thread.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(sum);
            latch.countDown();
        });
        try {
            latch.await();
            log.info("[{}]", list.get(FIRST));
        } catch (Exception e) {
            //
            e.printStackTrace();
        }
    }
}
