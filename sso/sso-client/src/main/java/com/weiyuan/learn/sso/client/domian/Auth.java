/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.sso.client.domian;

import java.util.Date;

/**
 * 授权对象
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/6/2 3:50 下午
 */
public class Auth {
    /**
     * 授权对象
     */
    private User user;

    /**
     * 授权随机码
     */
    private String code;

    /**
     * 过期时间
     */
    private Date expire;


}
