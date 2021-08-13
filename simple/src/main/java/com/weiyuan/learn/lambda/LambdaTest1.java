/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.lambda;

import com.weiyuan.learn.lambda.interfaces.LambdaNoneReturnNoneParameter;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/7/15 2:32 下午
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        LambdaNoneReturnNoneParameter fun1 = () -> System.out.println(1111);
        System.gc();
    }
}


