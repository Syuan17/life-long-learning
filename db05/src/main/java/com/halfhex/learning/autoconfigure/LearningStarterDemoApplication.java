package com.halfhex.learning.autoconfigure;

import com.google.common.collect.Lists;
import com.halfhex.learning.autoconfigure.bean.UserEntity;
import com.halfhex.learning.autoconfigure.service.HikariDemoService;
import com.halfhex.learning.autoconfigure.service.SimpleDemoService;
import com.halfhex.learning.autoconfigure.service.TransactionDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Syuan
 * @Date: 2021/9/5 11:54 下午
 */
@SpringBootApplication
@Slf4j
@EnableAsync
public class LearningStarterDemoApplication {

    private static final Integer LOOP_COUNT = 1_000_000;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        ApplicationContext context = SpringApplication.run(LearningStarterDemoApplication.class, args);
        // 基础demo
//        long start = System.currentTimeMillis();
//        SimpleDemoService simpleDemoService = context.getBean(SimpleDemoService.class);
//        try {
//            log.info("多次单条数据提交开始执行");
//            for (int i = 0; i < LOOP_COUNT; ++i) {
//                UserEntity userEntity = new UserEntity();
//                userEntity.setUserName("simpleDemoService_" + i);
//                userEntity.setEmail("simpleDemoService_" + i + "@mail.com");
//                userEntity.setPhone("187818" + i);
//                userEntity.setPassword("abcde" + i + "182737");
//                userEntity.setSalt("99" + i);
//                simpleDemoService.insert(userEntity);
//            }
//            log.info("多次单条数据提交 const => [{}]", System.currentTimeMillis() - start);
//        } catch (Exception e) {
//            log.warn("多次单条数据提交报错", e.fillInStackTrace());
//        }

        Long start = System.currentTimeMillis();
        log.info("初始化数据时间测试开始执行");
        for (int i = 0; i < 100; ++i) {
            List<UserEntity> list = Lists.newLinkedList();
            for (int j = 0; j < 10_000; ++j) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName("hikariDemoService" + (i * 10000 + j));
                userEntity.setEmail("hikariDemoService" + (i * 10000 + j) + "@mail.com");
                userEntity.setPhone("187818" + (i * 10000 + j));
                userEntity.setPassword("abcde" + (i * 10000 + j) + "182737");
                userEntity.setSalt("99" + (i * 10000 + j));
                list.add(userEntity);
            }
        }
        log.info("初始化数据时间测试 cost => [{}]", System.currentTimeMillis() - start);


        // 单次提交100w调数据
        log.info("事务批量提交开始执行");
        start = System.currentTimeMillis();
        TransactionDemoService transactionDemoService = context.getBean(TransactionDemoService.class);
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; ++i) {
            List<UserEntity> list = Lists.newLinkedList();
            for (int j = 0; j < 10_000; ++j) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName("transactionDemoService" + (i * 10000 + j));
                userEntity.setEmail("transactionDemoService" + (i * 10000 + j) + "@mail.com");
                userEntity.setPhone("187818" + (i * 10000 + j));
                userEntity.setPassword("abcde" + (i * 10000 + j) + "182737");
                userEntity.setSalt("99" + (i * 10000 + j));
                list.add(userEntity);
            }
            transactionDemoService.insertBatch(list, latch);
        }
        latch.await();
        log.info("事务批量异步提交 const => [{}]", System.currentTimeMillis() - start);


        // 分多次，使用线程池批量提交100w数据
//        start = System.currentTimeMillis();
//        log.info("分多次，使用线程池异步批量提交开始执行");
//        HikariDemoService hikariDemoService = context.getBean(HikariDemoService.class);
//        CountDownLatch countDownLatch = new CountDownLatch(100);
//        for (int i = 0; i < 100; ++ i) {
//            List<UserEntity> list = Lists.newLinkedList();
//            for (int j = 0; j < 10_000; ++ j) {
//                UserEntity userEntity = new UserEntity();
//                userEntity.setUserName("hikariDemoService" + (i * 10000 + j));
//                userEntity.setEmail("hikariDemoService" + (i * 10000 + j) + "@mail.com");
//                userEntity.setPhone("187818" + (i * 10000 + j));
//                userEntity.setPassword("abcde" + (i * 10000 + j) + "182737");
//                userEntity.setSalt("99" + (i * 10000 + j));
//                list.add(userEntity);
//            }
//            hikariDemoService.insertBatch(list, countDownLatch);
//        }
//        countDownLatch.await();
//        log.info("分多次，使用线程池异步批量提交 const => [{}]", System.currentTimeMillis() - start);

//        HikariDemoService hikariDemoService = context.getBean(HikariDemoService.class);
//        start = System.currentTimeMillis();
//        log.info("分多次，拼装SQL使用线程池异步提交开始执行");
//        CountDownLatch countDownLatch = new CountDownLatch(100);
//        for (int i = 0; i < 100; ++i) {
//            List<UserEntity> list = Lists.newLinkedList();
//            for (int j = 0; j < 10_000; ++j) {
//                UserEntity userEntity = new UserEntity();
//                userEntity.setUserName("hikariDemoServiceNew" + (i * 10000 + j));
//                userEntity.setEmail("hikariDemoServiceNew" + (i * 10000 + j) + "@mail.com");
//                userEntity.setPhone("187818" + (i * 10000 + j));
//                userEntity.setPassword("abcde" + (i * 10000 + j) + "182737");
//                userEntity.setSalt("99" + (i * 10000 + j));
//                list.add(userEntity);
//            }
//            hikariDemoService.insertByBatch(list, countDownLatch);
//        }
//        countDownLatch.await();
//        log.info("分多次，拼装SQL使用线程池异步提交开始执行 const => [{}]", System.currentTimeMillis() - start);
    }

}
