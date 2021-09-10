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

    public int insert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false",
                "root", "password");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(`id`, `user_name`) values(?, ?)");
        preparedStatement.setLong(1, 1);
        preparedStatement.setString(2, "张三");
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

    public UserEntity select() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false",
                "root", "password");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
        preparedStatement.setLong(1, 1);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            UserEntity userEntity = new UserEntity();
            while (resultSet.next()) {
                userEntity.setId(resultSet.getLong("id"));
                userEntity.setUserName(resultSet.getString("user_name"));
            }
            resultSet.close();
            preparedStatement.close();
            return userEntity;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
        return null;
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

    public int delete() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc_demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false",
                    "root", "password");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setLong(1, 1);
            try {
                return preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
