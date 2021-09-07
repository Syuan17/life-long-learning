package com.halfhex.learning.autoconfigure.scan;

import com.halfhex.learning.autoconfigure.bean.AnotherBeanService;
import com.halfhex.learning.autoconfigure.bean.XmlBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Syuan
 * @Date: 2021/9/6 12:05 上午
 */
@Component
public class AutoScanBeanService {

    @Autowired
    private XmlBeanService xmlBeanService;

    @Autowired
    private AnnoBeanService annoBeanService;

    @Autowired
    private AnotherBeanService anotherBeanService;

    @PostConstruct
    public void init() {
        System.out.println(xmlBeanService.getName() + " " + xmlBeanService.getValue());
        annoBeanService.print();
        System.out.println(anotherBeanService.getId() + " " + anotherBeanService.getCode());
    }

}
