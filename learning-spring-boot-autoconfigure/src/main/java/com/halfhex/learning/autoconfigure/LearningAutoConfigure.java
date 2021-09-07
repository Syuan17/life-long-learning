package com.halfhex.learning.autoconfigure;

import com.google.common.collect.Lists;
import com.halfhex.learning.autoconfigure.beans.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Syuan
 * @Date: 2021/9/6 11:06 下午
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = LearningProperties.class)
public class LearningAutoConfigure {

    @Autowired
    LearningProperties learningProperties;

    @Bean
    @ConditionalOnMissingBean
    public ISchool getSchool(Klass klass, Student monitor) {
        School school = new School();
        school.setClass1(klass);
        school.setStudent100(monitor);
        return school;
    }

    @Bean
    public Klass getKlass() {
        Klass klass = new Klass();
        klass.setStudents(Lists.newArrayList());
        return klass;
    }

    @Bean(name = "fourLines")
    public Student getStudent() {
        Student student = new Student();
        student.setName(learningProperties.getMonitorName());
        student.setId(learningProperties.getMonitorId());
        return student;
    }
}
