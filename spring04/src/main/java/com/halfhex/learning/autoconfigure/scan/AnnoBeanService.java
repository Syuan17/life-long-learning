package com.halfhex.learning.autoconfigure.scan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @Author: Syuan
 * @Date: 2021/9/5 11:51 下午
 */
@Service
// @Component
@Setter
@Getter
public class AnnoBeanService {

    public AnnoBeanService() {
        print();
    }

    public void print() {
        System.out.println("AnnoBean.pring: " + this.toString());
    }

}
