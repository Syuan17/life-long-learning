package concurrent03;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Syuan
 * @Date: 2021/8/29 5:25 下午
 */
@Slf4j
public class Work03 {

    private static final List<Integer> list = new ArrayList<>(1);

    private static final Integer FIRST = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();

        ExecutorService executor = Executors.newSingleThreadExecutor();
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
        });
        while (list.size() <= 0) {
            Thread.sleep(500);
        }
        log.info("[{}]", list.get(FIRST));
    }
}
