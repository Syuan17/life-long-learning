package com.halfhex.learning.autoconfigure;

import com.halfhex.learning.autoconfigure.bean.UserEntity;
import com.halfhex.learning.autoconfigure.service.HikariDemoService;
import com.halfhex.learning.autoconfigure.service.SimpleDemoService;
import com.halfhex.learning.autoconfigure.service.TransactionDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

/**
 * @Author: Syuan
 * @Date: 2021/9/5 11:54 下午
 */
@SpringBootApplication
@Slf4j
public class LearningStarterDemoApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = SpringApplication.run(LearningStarterDemoApplication.class, args);
        // 基础demo
        SimpleDemoService simpleDemoService = context.getBean(SimpleDemoService.class);
        simpleDemoService.insert();
        UserEntity entity1 = simpleDemoService.select();
        System.out.println(entity1);
        simpleDemoService.update();
        UserEntity entity2 = simpleDemoService.select();
        System.out.println(entity2);
        simpleDemoService.delete();
        // 事务 demo
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; ++ i) {
            TransactionDemoService transactionDemoService = context.getBean(TransactionDemoService.class);
            transactionDemoService.insert();
        }
        log.info("TransactionDemoService const => [{}]", System.currentTimeMillis() - start);
        // Hikari demo
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; ++ i) {
            HikariDemoService hikariDemoService = context.getBean(HikariDemoService.class);
            hikariDemoService.insert();
        }
        log.info("HikariDemoService const => [{}]", System.currentTimeMillis() - start);
    }

}
