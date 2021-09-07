package concurrent03;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: Syuan
 * @Date: 2021/8/29 5:21 下午
 */
@Slf4j
public class Work02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        ExecutorService executor = Executors.newCachedThreadPool();
        Future future = executor.submit((Callable) () -> {
            int sum = 0;
            log.info("start");
            for (int i = 0; i < 10; ++i) {
                sum += i;
                Thread.sleep(random.nextInt(500));
            }
            return sum;
        });
        log.info("[{}]", future.get());
    }

}
