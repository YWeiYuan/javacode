/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.tech.reflect.param.annotations;

import java.lang.annotation.*;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/19 10:37 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
    int order() default -1;
    int length() default -1;
    String type() default "";
}
