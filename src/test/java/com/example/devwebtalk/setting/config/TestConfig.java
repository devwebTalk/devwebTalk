package com.example.devwebtalk.setting.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 2021-09-07
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * Blog : https://kha0213.github.io/
 */
@Configuration
public class TestConfig implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TestConfig.applicationContext = applicationContext;
    }

    public static Object getBean(Class cls) {
        return TestConfig.applicationContext.getBean(cls);
    }
}
