/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.design.principle.openclose;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/20 2:45 下午
 */
public class JavaCourse implements ICourse {
    private Integer id;
    private String name;
    private Double price;

    public JavaCourse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    protected void protect() {
        System.out.println("111111");
    }
    private void pri() {
        System.out.println(11);
    }

    public static void stat() {
        System.out.println("stat。。。");
    }
}
