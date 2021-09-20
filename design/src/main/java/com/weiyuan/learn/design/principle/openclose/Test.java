/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.design.principle.openclose;

/**
 * 课程价格调整，在不改动ICourse 和它的实现类情况下：
 * 添加如下需求：价格打8折，输出原始价格。
 *
 * 不改动接口以及它默认的实现类是开闭原则指导的。
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/20 2:50 下午
 */
public class Test {
    public static void main(String[] args) {
        ICourse course = new JavaDiscountCourse(98, "JAVA", 100d);
        JavaDiscountCourse javaDiscountCourse = (JavaDiscountCourse) course;
        System.out.println("id:" + javaDiscountCourse.getId() + " name:" + javaDiscountCourse.getName() + " price:" + javaDiscountCourse.getPrice() + " 原始价格:" + javaDiscountCourse.originPrice());

    }
}
