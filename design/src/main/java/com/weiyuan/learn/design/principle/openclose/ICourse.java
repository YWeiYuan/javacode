/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.design.principle.openclose;

/**
 * 开闭原则的coding样例
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/20 1:57 下午
 */
public interface ICourse {
    /**
     * 课程id
     * @return id
     */
    Integer getId();

    /**
     * 课程名称
     * @return 名称
     */
    String getName();

    /**
     * 课程价格
     * @return 价格
     */
    Double getPrice();
}
