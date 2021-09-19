/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.param.pojo;

import com.weiyuan.learn.tech.reflect.param.AbstractApi;
import com.weiyuan.learn.tech.reflect.param.annotations.BankAPI;
import com.weiyuan.learn.tech.reflect.param.annotations.BankAPIField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 10:42 下午
 */
@BankAPI(url = "/bank/pay", desc = "支付接口")
@Data
public class PayApi extends AbstractApi {
    @BankAPIField(order = 1, type = "N", length = 20)
    private long userId;
    @BankAPIField(order = 2, type = "M", length = 10)
    private BigDecimal amount;
}
