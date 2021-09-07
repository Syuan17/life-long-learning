package com.halfhex.learning.autoconfigure;

import com.halfhex.learning.autoconfigure.Service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: Syuan
 * @Date: 2021/9/5 11:54 下午
 */
@SpringBootApplication
public class LearningStarterDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LearningStarterDemoApplication.class, args);
        context.getBean(DemoService.class).print();
    }

}
