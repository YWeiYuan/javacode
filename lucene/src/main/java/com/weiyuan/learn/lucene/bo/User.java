/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.lucene.bo;

import lombok.Data;

import java.util.UUID;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/18 2:58 下午
 */
@Data
public class User {
    public User(Long id, String userName, String sal) {
        this.id = id;
        this.userName = userName;
        this.sal = sal;
    }
    private Long id ;
    private String userName;
    private String sal;
}
