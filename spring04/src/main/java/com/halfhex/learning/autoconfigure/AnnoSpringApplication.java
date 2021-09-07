package com.halfhex.learning.autoconfigure;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Syuan
 * @Date: 2021/9/5 11:54 下午
 */
@Configuration
public class AnnoSpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

}
