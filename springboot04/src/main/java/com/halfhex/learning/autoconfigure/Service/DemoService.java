package com.halfhex.learning.autoconfigure.Service;

import com.halfhex.learning.autoconfigure.beans.ISchool;
import com.halfhex.learning.autoconfigure.beans.Klass;
import com.halfhex.learning.autoconfigure.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Syuan
 * @Date: 2021/9/7 11:12 下午
 */
@Service
public class DemoService {

    @Autowired
    private Student student;

    @Autowired
    private ISchool school;

    @Autowired
    private Klass klass;

    public void print() {
        school.ding();
        System.out.println();
        klass.dong();
        System.out.println();
        student.print();
    }

}
