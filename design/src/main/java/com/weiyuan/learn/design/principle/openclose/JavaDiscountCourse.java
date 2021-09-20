/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.design.principle.openclose;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/20 6:26 下午
 */
public class JavaDiscountCourse extends JavaCourse{
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    @Override
    public Double getPrice() {
        return super.getPrice() * 0.8;
    }

    public Double originPrice() {
        return super.getPrice();
    }
}
