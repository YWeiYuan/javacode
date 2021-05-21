/*
 * Copyright 2012-2022
 */
package com.xl.goahead.util;

import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/21 5:56 下午
 */
public class PropertiesUtil {
    /**
     * 加载配置文件
     * @param fileName 加载文件名，必须在resource文件件中
     * @return 配置信息
     */
    public static Properties loadProperties(final String fileName) {
        Properties props = new Properties();
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (null == in) {
                throw new FileNotFoundException("file " + fileName + "在resource文件夹中未找到");
            }
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void main(String[] args) {
        Properties properties = loadProperties("screw.properties");
        String username = properties.getProperty("datasource.username");
        System.out.println(username);
    }
}
