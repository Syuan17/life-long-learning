package com.halfhex.learning.autoconfigure.service;

import com.halfhex.learning.autoconfigure.bean.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Syuan
 * @Date: 2021/9/10 2:06 下午
 */
@Service
@Slf4j
public class HikariDemoService {

    @Autowired
    private DataSource dataSource;

    @Async
    public void insertBatch(List<UserEntity> list, CountDownLatch countDownLatch) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(Boolean.FALSE);
        PreparedStatement ps1 = connection.prepareStatement("INSERT INTO user(`user_name`, `phone`, `email`, `password`, `salt`) " +
                "values(?, ?, ?, ?, ?)");
        for (UserEntity userEntity : list) {
            ps1.setString(1, userEntity.getUserName());
            ps1.setString(2, userEntity.getPhone());
            ps1.setString(3, userEntity.getEmail());
            ps1.setString(4, userEntity.getPassword());
            ps1.setString(5, userEntity.getSalt());
            ps1.addBatch();
        }
        try {
            int[] ints = ps1.executeBatch();
            connection.commit();
            return ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
            ps1.close();
            connection.setAutoCommit(Boolean.TRUE);
            connection.close();
        }
        return ;
    }

    @Async
    public void insertByBatch(List<UserEntity> list, CountDownLatch latch) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(Boolean.FALSE);
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO user(`user_name`, `phone`, `email`, `password`, `salt`) " +
                "values(?, ?, ?, ?, ?)");
        for (int i = 1; i < list.size(); ++ i) {
            sqlBuilder.append(", (?, ?, ?, ?, ?)");
        }
        PreparedStatement ps1 = connection.prepareStatement(sqlBuilder.toString());
        int index = 1;
        for (UserEntity userEntity : list) {
            ps1.setString(index ++, userEntity.getUserName());
            ps1.setString(index ++, userEntity.getPhone());
            ps1.setString(index ++, userEntity.getEmail());
            ps1.setString(index ++, userEntity.getPassword());
            ps1.setString(index ++, userEntity.getSalt());
        }
        try {
            int res = ps1.executeUpdate();
            connection.commit();
            return ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps1.close();
            connection.setAutoCommit(Boolean.TRUE);
            connection.close();
            latch.countDown();
        }
        return ;
    }

}
