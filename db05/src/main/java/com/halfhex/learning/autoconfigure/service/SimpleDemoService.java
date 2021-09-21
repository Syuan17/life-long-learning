package com.halfhex.learning.autoconfigure.service;

import com.halfhex.learning.autoconfigure.bean.UserEntity;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * @Author: Syuan
 * @Date: 2021/9/8 3:23 下午
 */
@Service
public class SimpleDemoService {

    public void init() throws ClassNotFoundException, SQLException {
    }

    public int insert(UserEntity userEntity) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rf_mall?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false",
                "root", "password");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(`user_name`, `phone`, `email`, `password`, `salt`) " +
                "values(?, ?, ?, ?, ?)");
        preparedStatement.setString(1, userEntity.getUserName());
        preparedStatement.setString(2, userEntity.getPhone());
        preparedStatement.setString(3, userEntity.getEmail());
        preparedStatement.setString(4, userEntity.getPassword());
        preparedStatement.setString(5, userEntity.getSalt());
        try {
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
        return 0;
    }

}
