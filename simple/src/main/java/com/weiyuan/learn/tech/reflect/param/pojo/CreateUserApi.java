/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.param.pojo;

import com.weiyuan.learn.tech.reflect.param.AbstractApi;
import com.weiyuan.learn.tech.reflect.param.annotations.BankAPI;
import com.weiyuan.learn.tech.reflect.param.annotations.BankAPIField;
import lombok.Data;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 10:39 下午
 */
@BankAPI(url = "/bank/createUser", desc = "创建用户接口")
@Data
public class CreateUserApi extends AbstractApi {
    @BankAPIField(order = 1, type = "S", length = 10)
    private String name;
    @BankAPIField(order = 2, type = "S", length = 18)
    private String identity;
    /**
     * 注意这里的order需要按照API表格中的顺序
     */
    @BankAPIField(order = 4, type = "S", length = 11)
    private String mobile;
    @BankAPIField(order = 3, type = "N", length = 5)
    private int age;
}