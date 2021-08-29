package concurrent03;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Syuan
 * @Date: 2021/8/29 4:15 下午
 */
@Slf4j
public class Work01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();

        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                log.info("start");
                for (int i = 0; i < 10; ++i) {
                    sum += i;
                    Thread.sleep(random.nextInt(500));
                }
                return sum;
            }
        });
        new Thread(futureTask).start();
        log.info("[{}]", futureTask.get());
    }

}
