package com.halfhex.learning.autoconfigure.service;

import com.halfhex.learning.autoconfigure.bean.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * @Author: Syuan
 * @Date: 2021/9/8 3:23 下午
 */
@Service
@Slf4j
public class TransactionDemoService {

    public void init() throws ClassNotFoundException, SQLException {
    }

    public int insert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false",
                "root", "password");
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
            connection.close();
        }
        return 0;
    }

    public int update() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false",
                    "root", "password");
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
