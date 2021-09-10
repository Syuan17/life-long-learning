package com.halfhex.learning.autoconfigure.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: Syuan
 * @Date: 2021/9/10 2:06 下午
 */
@Service
@Slf4j
public class HikariDemoService {

    @Autowired
    private DataSource dataSource;

    public int insert() throws ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(Boolean.FALSE);
        PreparedStatement ps1 = connection.prepareStatement("INSERT INTO user(`user_name`) values(?)");
        ps1.setString(1, "张三");
        ps1.addBatch();
        ps1.setString(1, "李四");
        ps1.addBatch();
        ps1.setString(1, "王五");
        ps1.addBatch();
        try {
            int[] ints = ps1.executeBatch();
            connection.commit();
//            log.info("ints => [{}]", ints);
            return ints.length;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps1.close();
            connection.setAutoCommit(Boolean.TRUE);
            connection.close();
        }
        return 0;
    }

    public int update() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET user_name = ? WHERE id = ?");
            preparedStatement.setString(1, "李四");
            preparedStatement.setLong(2, 1);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
