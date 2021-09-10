package com.halfhex.learning.autoconfigure.bean;

import lombok.Data;

/**
 * @Author: Syuan
 * @Date: 2021/9/9 3:30 下午
 */
@Data
public class UserEntity {

    private Long id;

    private String userName;

    @Override
    public String toString() {
        return String.format("[id => %d], [userName => %s]", id, userName);
    }
}
