/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.io.serialize.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 写文件示例
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/15 12:11 上午
 */
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 9037212854547924281L;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    private String name;
    private Integer age;

    private String address;
}
