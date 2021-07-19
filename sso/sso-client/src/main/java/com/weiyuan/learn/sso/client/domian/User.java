/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.sso.client.domian;

/**
 * 登录用户
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/6/2 3:53 下午
 */
public class User {
    /**
     * id
     */
    private Long id;
    /**
     * 登录名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
