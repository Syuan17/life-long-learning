package com.halfhex.learning.autoconfigure.bean;

import lombok.Data;

/**
 * @Author: Syuan
 * @Date: 2021/9/6 12:00 上午
 */
@Data
public class XmlBeanService {

    public XmlBeanService() {
        System.out.println("create bean with No Args Constructor");
    }

    public XmlBeanService(String name, String value) {
        this.name = name;
        this.value = value;
        System.out.println("create bean with All Args Constructor");
    }

    private String name;

    private String value;

}
