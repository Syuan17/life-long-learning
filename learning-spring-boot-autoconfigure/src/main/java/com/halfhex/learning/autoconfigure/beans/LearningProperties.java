package com.halfhex.learning.autoconfigure.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Syuan
 * @Date: 2021/9/7 10:34 下午
 */
@ConfigurationProperties(value = "halfhex.learning")
@Setter
@Getter
public class LearningProperties {

    private String ip;

    private String label;

    private String schoolName;

    private String monitorName;

    private Integer monitorId;

}
