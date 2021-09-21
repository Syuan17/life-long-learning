package com.halfhex.learning.autoconfigure.service;

import com.halfhex.learning.autoconfigure.bean.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Syuan
 * @Date: 2021/9/8 3:23 下午
 */
@Service
@Slf4j
public class TransactionDemoService {

    public void init() throws ClassNotFoundException, SQLException {
    }

    @Async
    public void insertBatch(List<UserEntity> list, CountDownLatch latch) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rf_mall?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false",
                "root", "password");
        connection.setAutoCommit(Boolean.FALSE);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(`user_name`, `phone`, `email`, `password`, `salt`) " +
                "values(?, ?, ?, ?, ?)");
        for (UserEntity userEntity : list) {
            preparedStatement.setString(1, userEntity.getUserName());
            preparedStatement.setString(2, userEntity.getPhone());
            preparedStatement.setString(3, userEntity.getEmail());
            preparedStatement.setString(4, userEntity.getPassword());
            preparedStatement.setString(5, userEntity.getSalt());
            preparedStatement.addBatch();
        }
        try {
            int[] ints = preparedStatement.executeBatch();
            connection.commit();
//            log.info("ints => [{}]", ints);
            return ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
            latch.countDown();
        }
        return ;
    }
}
